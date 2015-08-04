package uni.edu.juegosdelhambre.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.OnClick;
import uni.edu.juegosdelhambre.R;
import uni.edu.juegosdelhambre.WordSearchActivity;

/**
 * @author Jeans K. Real, 8/3/15.
 */
public class PauseDialogFragment extends DialogFragment {

    private PauseDialogListener mListener;

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        WordSearchActivity activity = (WordSearchActivity) getActivity();
        LayoutInflater inflater = activity.getLayoutInflater();

        View layout = inflater.inflate(R.layout.fragment_gameplay_dialog, null);

        TextView scorePauseTextView = (TextView) layout.findViewById(R.id.tvScorePause);
        scorePauseTextView.setText(Integer.toString(activity.getScore()));

        TextView timerPauseTextView = (TextView) layout.findViewById(R.id.tvTimePause);
        timerPauseTextView.setText(Long.toString(activity.getTimeRemaining() / 1000 + 1));

        builder.setView(layout);
        Dialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);

        ButterKnife.bind(this, layout);

        return dialog;
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        mListener.onDialogResume();
        super.onCancel(dialog);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (PauseDialogListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement PauseDialogListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface PauseDialogListener {
        void onDialogQuit();
        void onDialogResume();
        void onDialogRestart();
    }

    @OnClick(R.id.btnResume) public void Resume() {
        getDialog().dismiss();
        mListener.onDialogResume();
    }

    @OnClick(R.id.btnRestart) public void Restart() {
        getDialog().dismiss();
        mListener.onDialogRestart();
    }

    @OnClick(R.id.btnExitToMenu) public void Exit() {
        getDialog().dismiss();
        mListener.onDialogQuit();
    }

}
