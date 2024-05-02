package com.example.ns_fe_project.view.custom

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.Window
import com.example.ns_fe_project.databinding.CustomDialogBinding

class CustomDialog(
    context: Context,
    private val customDialogContext: Context = context
): Dialog(context) {

    private val binding = CustomDialogBinding.inflate(layoutInflater)

    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setCancelable(false)
        setContentView(binding.root)
    }

    fun build(): CustomDialog {
        return CustomDialog(customDialogContext).apply {
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }

    fun setDescription(desc: String): CustomDialog {
        binding.tvDesc.apply {
            text = desc
            visibility = View.VISIBLE
        }

        return this
    }

    fun withOnlyButton(
        buttonText: String,
        buttonAction: DialogInterface.OnClickListener
    ): CustomDialog {
        binding.apply {
            tvClosePopup.text = buttonText
            btnClose.setOnClickListener {
                buttonAction.onClick(this@CustomDialog, DialogInterface.BUTTON_NEGATIVE)
                cdProductDetail.visibility = View.GONE
                dismiss()
            }
        }
        return this
    }

    fun withSetCancelableOnTouchOutside(canceledOnTouchOutside: Boolean): CustomDialog {
        setCanceledOnTouchOutside(canceledOnTouchOutside)
        return this
    }
}