package ua.org.rshu.fmi.mobapp.view.activity.noteeditor.impl;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.EditText;

import com.jakewharton.rxbinding2.widget.RxTextView;
import com.orhanobut.logger.Logger;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ua.org.rshu.fmi.mobapp.R;
import ua.org.rshu.fmi.mobapp.service.notepadservices.core.NoteService;
import ua.org.rshu.fmi.mobapp.service.notepadservices.form.NoteForm;
import ua.org.rshu.fmi.mobapp.view.activity.main.MainActivityImpl;
import ua.org.rshu.fmi.mobapp.view.activity.noteeditor.NoteEditorActivity;
import ua.org.rshu.fmi.mobapp.view.activity.noteeditor.NoteEditorPresenter;
import ua.org.rshu.fmi.mobapp.view.util.markdownconverter.MarkdownConverter;
import ua.org.rshu.fmi.mobapp.view.util.markdownconverter.impl.MarkdownConverterImpl;

/**
 * @author Vadim Boitsov <vadimboitsov1@gmail.com>
 */

class NoteEditorPresenterImpl implements NoteEditorPresenter {
    private static final String TAG = "NoteEditorPresenter";

    private NoteService mNoteService;

    private NoteEditorActivity mNoteEditorActivity;

    private MarkdownConverter mMarkdownConverter;

    private Disposable mEditorEditTextDisposable;

    NoteEditorPresenterImpl(NoteService noteService) {
        mNoteService = noteService;
        mMarkdownConverter = new MarkdownConverterImpl();
    }

    @Override
    public void subscribeEditorForPreview(EditText editText) {
        mEditorEditTextDisposable = RxTextView.textChanges(editText)
                .debounce(300, TimeUnit.MILLISECONDS)
                .map(text -> mMarkdownConverter.getParsedHtml(text.toString()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(html -> mNoteEditorActivity.loadPreview(html));
    }

    @Override
    public void unsubscribeEditorForPreview() {
        if (mEditorEditTextDisposable == null || mEditorEditTextDisposable.isDisposed()) {
            return;
        }
        mEditorEditTextDisposable.dispose();
        Logger.d("Note editor is unsubscribed for preview");
    }

    @Override
    public void bindView(NoteEditorActivity noteEditorActivity) {
        this.mNoteEditorActivity = noteEditorActivity;
        Logger.d("Node editor is binded to its presenter.");
    }

    @Override
    public void unbindView() {
        mNoteEditorActivity = null;
        Logger.d("Node editor is unbinded to its presenter.");
    }

    @Override
    public void saveNewNote(String title, String content, String htmlContent) {
        NoteForm noteForm = new NoteForm(title, content, htmlContent);

        Flowable.just(noteForm)
                .filter(noteForm1 -> {
                    if (TextUtils.isEmpty(noteForm1.getTitle())){
                        Snackbar.make(((AppCompatActivity) mNoteEditorActivity).findViewById(R.id.relative_layout_container_activity_note_editor),"Заповніть назву", Snackbar.LENGTH_LONG).show();
                        return false;
                    }
                    return true;
                })
                .doOnNext(noteFormToSend -> mNoteService.openConnection())
                .map(noteFormToSend -> mNoteService.create(noteFormToSend))
                .doOnNext(stringOptional -> mNoteService.closeConnection())
                .filter(stringOptional -> {
                    if (stringOptional.isPresent()){
                        ((AppCompatActivity) mNoteEditorActivity).startActivity(new Intent(((AppCompatActivity) mNoteEditorActivity), MainActivityImpl.class));
                        ((AppCompatActivity) mNoteEditorActivity).finish();
                        return false;
                    }
                    return true;
                })
                .subscribe(stringOptional -> {
                    Logger.wtf("New note is note created due to unforeseen circumstances.");
                    throw new RuntimeException();
                });
    }

    @Override
    public void updateNote(String id, String title, String content, String htmlContent) {
        NoteForm noteForm = new NoteForm(title, content, htmlContent);

        Flowable.just(noteForm)
                .filter(noteForm1 -> {
                    if (TextUtils.isEmpty(noteForm1.getTitle())){
                        Snackbar.make(((AppCompatActivity) mNoteEditorActivity).findViewById(R.id.relative_layout_container_activity_note_editor),"Fill the title", Snackbar.LENGTH_LONG).show();
                        return false;
                    }
                    return true;
                })
                .doOnNext(noteFormToSend -> mNoteService.openConnection())
                .map(noteFormToSend -> mNoteService.update(id, noteForm))
                .doOnNext(stringOptional -> mNoteService.closeConnection())
                .filter(isUpdated -> {
                    if (isUpdated){
                        ((AppCompatActivity) mNoteEditorActivity).startActivity(new Intent(((AppCompatActivity) mNoteEditorActivity), MainActivityImpl.class));
                        ((AppCompatActivity) mNoteEditorActivity).finish();
                        return false;
                    }
                    return true;
                })
                .subscribe(stringOptional -> {
                    Logger.wtf("Note is note updated due to unforeseen circumstances.");
                    throw new RuntimeException();
                });
    }

}
