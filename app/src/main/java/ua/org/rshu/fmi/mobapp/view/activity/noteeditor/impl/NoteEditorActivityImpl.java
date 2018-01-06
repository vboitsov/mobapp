package ua.org.rshu.fmi.mobapp.view.activity.noteeditor.impl;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TabHost;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ua.org.rshu.fmi.mobapp.FMIApplication;
import ua.org.rshu.fmi.mobapp.R;
import ua.org.rshu.fmi.mobapp.persistent.notepadpersistent.entity.Note;
import ua.org.rshu.fmi.mobapp.service.notepadservices.core.NoteService;
import ua.org.rshu.fmi.mobapp.view.activity.main.MainActivityImpl;
import ua.org.rshu.fmi.mobapp.view.activity.noteeditor.NoteEditorActivity;
import ua.org.rshu.fmi.mobapp.view.activity.noteeditor.NoteEditorPresenter;

/**
 * @author Vadim Boitsov <vadimboitsov1@gmail.com>
 */

public class NoteEditorActivityImpl extends AppCompatActivity implements NoteEditorActivity {

    @Inject NoteService noteService;

    @BindView(R.id.toolbar_main) Toolbar mainToolbar;

    @BindView(R.id.edit_text_title) EditText mTitleEditText;

    @BindView(R.id.edit_text_editor) EditText mEditorEditText;

    @BindView(R.id.web_view_preview) WebView mPreviewWebView;

    @BindView(R.id.tab_host) TabHost tabHost;

    private static final String EDITOR_TEXT_KEY = "editorText";
    private static final String TITLE_TEXT_KEY = "titleText";
    private static final String CURRENT_TAB_KEY = "currentTab";
    private static final String EDITOR_TAB_ID = "Редактор";
    private static final String PREVIEW_TAB_ID = "Попередній перегляд";
    private static final String MIME_TYPE = "text/html";
    private static final String ENCODING = "charset=UTF-8";

    private NoteEditorPresenter mNoteEditorPresenter;
    private String mHtmlText = "";
    private String noteId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editor);
        ButterKnife.bind(this);
        FMIApplication.getsAppComponent().inject(this);
        mPreviewWebView.getSettings().setJavaScriptEnabled(true);
        setUpToolbar();
        initTabs();
        restoreSavedInstance(savedInstanceState);

        if (getIntent().getExtras() != null) {
            Note note = getIntent().getParcelableExtra("noteForEdit");
            noteId = note.getId();
            mTitleEditText.setText(note.getTitle());
            mEditorEditText.setText(note.getContent());
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (mEditorEditText != null) {
            outState.putString(EDITOR_TEXT_KEY, mEditorEditText.getText().toString());
        }
        if (mTitleEditText != null) {
            outState.putString(TITLE_TEXT_KEY, mTitleEditText.getText().toString());
        }
        if (tabHost != null) {
            outState.putInt(CURRENT_TAB_KEY, tabHost.getCurrentTab());
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mNoteEditorPresenter == null) {
            mNoteEditorPresenter = new NoteEditorPresenterImpl(noteService);
        }
        mNoteEditorPresenter.bindView(this);
        mNoteEditorPresenter.subscribeEditorForPreview(mEditorEditText);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mNoteEditorPresenter.unsubscribeEditorForPreview();
        mNoteEditorPresenter.unbindView();
    }

    @Override
    public void loadPreview(String html) {
        mHtmlText = html;
        mPreviewWebView.loadDataWithBaseURL(null, html, MIME_TYPE, ENCODING, null);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_note_editor, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.item_done) {
            if (TextUtils.isEmpty(noteId)) {
                mNoteEditorPresenter.saveNewNote(
                        mTitleEditText.getText().toString(),
                        mEditorEditText.getText().toString(),
                        mHtmlText);
            } else {
                mNoteEditorPresenter.updateNote(
                        noteId,
                        mTitleEditText.getText().toString(),
                        mEditorEditText.getText().toString(),
                        mHtmlText);
            }
        }
        return false;
    }

    private void setUpToolbar() {
        setSupportActionBar(mainToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mainToolbar.setNavigationOnClickListener(v -> {
            this.startActivity(new Intent(this, MainActivityImpl.class));
            this.finish();
        });
    }

    /**
     * A method which restores activity state
     *
     * @param savedInstanceState a bundle with restored data.
     */
    private void restoreSavedInstance(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(EDITOR_TEXT_KEY)) {
                mEditorEditText.setText(savedInstanceState.getString(EDITOR_TEXT_KEY));
            }
            if (savedInstanceState.containsKey(TITLE_TEXT_KEY)) {
                mTitleEditText.setText(savedInstanceState.getString(TITLE_TEXT_KEY));
            }
            if (savedInstanceState.containsKey(CURRENT_TAB_KEY)) {
                tabHost.setCurrentTab(savedInstanceState.getInt(CURRENT_TAB_KEY));
            }
        }
    }

    /**
     * A method which initializes editor and preview tabs.
     */
    private void initTabs() {
        tabHost.setup();

        TabHost.TabSpec tabSpec = tabHost.newTabSpec(EDITOR_TAB_ID);
        tabSpec.setContent(R.id.tab_editor);
        tabSpec.setIndicator(EDITOR_TAB_ID);
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec(PREVIEW_TAB_ID);
        tabSpec.setContent(R.id.tab_preview);
        tabSpec.setIndicator(PREVIEW_TAB_ID);
        tabHost.addTab(tabSpec);

        tabHost.setOnTabChangedListener(tabId -> hideSoftKeyboard());
    }

    /**
     * A method which hides a soft keyboard when tabs are switched.
     */
    private void hideSoftKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
