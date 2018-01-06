package ua.org.rshu.fmi.mobapp.view.fragment.notecontent;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import ua.org.rshu.fmi.mobapp.FMIApplication;
import ua.org.rshu.fmi.mobapp.R;
import ua.org.rshu.fmi.mobapp.persistent.notepadpersistent.entity.Note;
import ua.org.rshu.fmi.mobapp.service.notepadservices.core.NoteService;
import ua.org.rshu.fmi.mobapp.view.activity.noteeditor.impl.NoteEditorActivityImpl;
import ua.org.rshu.fmi.mobapp.view.util.consts.BundleKeysConst;


/**
 * @author Vadim Boitsov <vadimboitsov1@gmail.com>
 */

public class NoteContentFragmentImpl extends Fragment {

    @Inject NoteService noteService;

    @BindView(R.id.text_view_note_title) TextView mEditTextTitle;

    @BindView(R.id.web_view_note_content) WebView mNoteContentWebView;

    private Note mSelectNote;

    private Unbinder mUnbinder;

    private static final String MIME_TYPE = "text/html";
    private static final String ENCODING = "charset=UTF-8";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_note_content, container, false);
        mUnbinder = ButterKnife.bind(this, rootView);
        FMIApplication.getsAppComponent().inject(this);
        setUpToolbar();
        getParcelableDataAndSetInView();
        mNoteContentWebView.getSettings().setJavaScriptEnabled(true);
        return rootView;
    }

    /**
     * A method which hears when user click on button and goes one fragment below from current
     */
    @OnClick(R.id.im_btn_arrow_back_to_list)
    public void backToPreviousFragment() {
        getActivity().onBackPressed();
    }

    @OnClick(R.id.im_btn_edit_note)
    public void openNoteEditor(){
        Intent intent = new Intent(getActivity(), NoteEditorActivityImpl.class);
        intent.putExtra("noteForEdit", mSelectNote);
        getActivity().startActivity(intent);
    }

    @OnClick(R.id.im_btn_delete_note)
    public void removeNote() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Видалення")
                .setMessage("Видалити нотаток?")
                .setIcon(R.drawable.ic_remove_circle_black_24dp)
                .setCancelable(false)
                .setNegativeButton("Відмінити",
                        (dialog, id) -> dialog.cancel())
                .setPositiveButton("Видалити", ((dialog, which) -> {
                    noteService.openConnection();
                    noteService.remove(mSelectNote.getId());
                    noteService.closeConnection();
                    dialog.cancel();
                    getActivity().onBackPressed();
                }));
        AlertDialog alert = builder.create();
        alert.show();
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != null){
            mUnbinder.unbind();
        }
    }

    /**
     * A method which gets data from bundle and set them in defined view element
     */
    private void getParcelableDataAndSetInView() {
         mSelectNote = getArguments().getParcelable(BundleKeysConst.BUNDLE_NOTE_OBJECT_KEY);

        mEditTextTitle.setText(mSelectNote.getTitle());

        mNoteContentWebView.loadDataWithBaseURL(null, mSelectNote.getHtmlContent(), MIME_TYPE, ENCODING, null);

    }

    /**
     * A method which sets defined view of main toolbar
     */
    private void setUpToolbar() {
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
    }
}
