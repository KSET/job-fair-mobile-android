package com.undabot.jobfair.scanqr.view

import android.Manifest.permission.CAMERA
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraCharacteristics
import android.hardware.camera2.CameraManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.zxing.Result
import com.undabot.jobfair.R
import com.undabot.jobfair.core.di.ApplicationComponent
import com.undabot.jobfair.core.view.BaseActivity
import com.undabot.jobfair.review.view.StudentReviewActivity
import com.undabot.jobfair.scanqr.di.ScanQrModule
import com.undabot.jobfair.scanqr.mapper.QrCodeDataJsonMapper
import com.undabot.jobfair.scanqr.view.custom.QrScannerView
import kotlinx.android.synthetic.main.scan_qr_activity.*
import kotlinx.android.synthetic.main.toolbar_details.*
import me.dm7.barcodescanner.zxing.ZXingScannerView
import javax.inject.Inject


class ScanQrActivity : BaseActivity(), ZXingScannerView.ResultHandler {

    companion object {
        private const val CAMERA_ID_KEY = "CAMERA_ID_KEY"
        private const val REQUEST_CAMERA = 100

        fun startWith(context: Context) {
            val intent = Intent(context, ScanQrActivity::class.java)
            context.startActivity(intent)
        }
    }

    private var cameraId: Int? = null
    private lateinit var scannerView: ZXingScannerView

    @Inject lateinit var qrCodeDataJsonMapper: QrCodeDataJsonMapper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.scan_qr_activity)
        setupToolbar()

        savedInstanceState?.getInt(CAMERA_ID_KEY)?.let {
            cameraId = if (it > 0) {
                it
            } else {
                getBackCameraId()
            }
        }

        scannerView = QrScannerView(this)
        scannerContainer.addView(scannerView)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !checkPermission()) {
            requestPermission()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            REQUEST_CAMERA -> if (grantResults.isNotEmpty()) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    longToast(R.string.camera_permission_granted)
                } else {
                    longToast(R.string.camera_permission_denied)

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (shouldShowRequestPermissionRationale(CAMERA)) {
                            showMessageOKCancel(
                                message = getString(R.string.camera_permission_allow_permission),
                                okListener = DialogInterface.OnClickListener { _, _ ->
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                        requestPermissions(arrayOf(CAMERA), REQUEST_CAMERA)
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }

    public override fun onResume() {
        super.onResume()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkPermission()) {
            scannerView.setResultHandler(this)
            scannerView.startCamera()
        }
    }

    public override fun onDestroy() {
        super.onDestroy()
        scannerView.stopCamera()
    }

    public override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        cameraId?.let {
            outState.putInt(CAMERA_ID_KEY, it)
        }
    }

    override fun handleResult(rawResult: Result?) {
        val json = rawResult?.text.orEmpty()
        try {
            val qrCodeData = qrCodeDataJsonMapper.fromJson(json)
            StudentReviewActivity.startWith(this, qrCodeData)
            finish()
        } catch (e: Throwable) {
            showMessage(
                message = getString(R.string.general_error_message),
                onDismissed = { finish() }
            )
        }
    }

    override fun injectToAppComponent(applicationComponent: ApplicationComponent) {
        applicationComponent.plus(ScanQrModule()).inject(this)
    }

    private fun setupToolbar() {
        findViewById<Toolbar>(R.id.toolbar).apply {
            toolbarTitle.text = getString(R.string.scan_qr_code)
            setNavigationOnClickListener { super.onBackPressed() }
        }
    }

    private fun showMessageOKCancel(message: String, okListener: DialogInterface.OnClickListener) {
        AlertDialog.Builder(this)
            .setMessage(message)
            .setPositiveButton(R.string.ok, okListener)
            .setNegativeButton(R.string.cancel, null)
            .create()
            .show()
    }

    private fun checkPermission(): Boolean {
        return ContextCompat.checkSelfPermission(applicationContext, CAMERA) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(this, arrayOf(CAMERA), REQUEST_CAMERA)
    }

    private fun getBackCameraId(): Int? {
        try {
            val manager = getSystemService(Context.CAMERA_SERVICE) as CameraManager
            for (cameraId in manager.cameraIdList) {
                val characteristics = manager.getCameraCharacteristics(cameraId)
                val cameraOrientation = characteristics.get(CameraCharacteristics.LENS_FACING)!!
                if (cameraOrientation == CameraCharacteristics.LENS_FACING_BACK) {
                    return Integer.parseInt(cameraId)
                }
            }
        } catch (e: CameraAccessException) {
            e.printStackTrace()
        }

        return null
    }

    private fun longToast(@StringRes message: Int) {
        Toast.makeText(applicationContext, getString(message), Toast.LENGTH_LONG).show()
    }
}
