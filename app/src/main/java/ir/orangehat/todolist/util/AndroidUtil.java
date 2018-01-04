package ir.orangehat.todolist.util;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.TextView;

import ir.orangehat.todolist.R;

/**
 * AndroidUtil
 */

public class AndroidUtil {

    public static void showSnackUndo(View view) {
        if (view == null || view.getContext() == null) {
            return;
        }

        UndoClickHandler undoClickHandler = new UndoClickHandler(view.getContext());

        Snackbar snackbar = Snackbar.make(view, undoClickHandler.message, Snackbar.LENGTH_LONG);
        snackbar.setAction(undoClickHandler.actionMessage, undoClickHandler);
        ((TextView) snackbar.getView().findViewById(android.support.design.R.id.snackbar_text)).setTextColor(Color.WHITE);
        snackbar.show();
    }

    private static class UndoClickHandler implements View.OnClickListener {

        final String message;
        final String actionMessage;

        UndoClickHandler(Context context) {
            this.message = context.getString(R.string.title_activity_main2);
            this.actionMessage = context.getString(R.string.accept_button);
        }

        @Override
        public void onClick(View view) {
            // do something
        }
    }

    public static void showSnack(@NonNull View view, @NonNull CharSequence message){

        Snackbar snackbar =Snackbar.make(view,message, Snackbar.LENGTH_SHORT);
        snackbar.show();
    }


}
