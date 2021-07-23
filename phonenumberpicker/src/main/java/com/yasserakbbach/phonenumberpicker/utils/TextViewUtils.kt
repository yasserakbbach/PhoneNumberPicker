package com.yasserakbbach.phonenumberpicker.utils

import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView

/**
 * To disable copy/past
 */
fun TextView.disableCopyPaste() {

    isLongClickable = false
    setTextIsSelectable(false)

    customSelectionActionModeCallback = object : ActionMode.Callback {

        override fun onCreateActionMode(mode: ActionMode?, menu: Menu) = false

        override fun onPrepareActionMode(mode: ActionMode?, menu: Menu) = false

        override fun onActionItemClicked(mode: ActionMode?, item: MenuItem) = false

        override fun onDestroyActionMode(mode: ActionMode?) {}
    }
}