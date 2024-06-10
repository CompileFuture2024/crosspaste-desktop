package com.clipevery.ui.base

interface DialogService {

    var dialogs: MutableList<ClipDialog>

    fun pushDialog(dialog: ClipDialog)

    fun popDialog()
}
