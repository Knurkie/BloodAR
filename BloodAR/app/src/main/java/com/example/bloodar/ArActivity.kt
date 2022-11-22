package com.example.bloodar

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import io.github.sceneview.ar.ArSceneView
import io.github.sceneview.ar.node.ArModelNode
import io.github.sceneview.ar.node.PlacementMode
import io.github.sceneview.math.Position
import io.github.sceneview.utils.doOnApplyWindowInsets
import io.github.sceneview.utils.setFullScreen

class ArActivity : AppCompatActivity(R.layout.activity_ar) {

    lateinit var sceneView: ArSceneView
    lateinit var loadingView: View
    lateinit var placeModelButton: ExtendedFloatingActionButton
    lateinit var newModelButton: ExtendedFloatingActionButton

    data class Model(
        val fileLocation: String,
        val scaleUnits: Float? = null,
        val placementMode: PlacementMode = PlacementMode.BEST_AVAILABLE,
        val applyPoseRotation: Boolean = true
    )

    val models = listOf(
        Model("models/spiderbot.glb"),
        Model(
            fileLocation = "https://storage.googleapis.com/ar-answers-in-search-models/static/Tiger/model.glb",
            // Display the Tiger with a size of 3 m long
            scaleUnits = 2.5f,
            placementMode = PlacementMode.BEST_AVAILABLE,
            applyPoseRotation = false
        ),
        Model(
            fileLocation = "https://sceneview.github.io/assets/models/DamagedHelmet.glb",
            placementMode = PlacementMode.INSTANT,
            scaleUnits = 0.5f
        ),
        Model(
            fileLocation = "https://storage.googleapis.com/ar-answers-in-search-models/static/GiantPanda/model.glb",
            placementMode = PlacementMode.PLANE_HORIZONTAL,
            // Display the Tiger with a size of 1.5 m height
            scaleUnits = 1.5f
        ),
        Model(
            fileLocation = "https://sceneview.github.io/assets/models/Spoons.glb",
            placementMode = PlacementMode.PLANE_HORIZONTAL_AND_VERTICAL,
            // Keep original model size
            scaleUnits = null
        ),
        Model(
            fileLocation = "https://sceneview.github.io/assets/models/Halloween.glb",
            placementMode = PlacementMode.PLANE_HORIZONTAL,
            scaleUnits = 2.5f
        ),
    )
    var modelIndex = 0
    var modelNode: ArModelNode? = null

    var isLoading = false
        set(value) {
            field = value
            loadingView.isGone = !value
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFullScreen(
            findViewById(R.id.rootView),
            fullScreen = true,
            hideSystemBars = false,
            fitsSystemWindows = false
        )

//        setSupportActionBar(findViewById<Toolbar>(R.id.toolbar)?.apply {
//            doOnApplyWindowInsets { systemBarsInsets ->
//                (layoutParams as ViewGroup.MarginLayoutParams).topMargin = systemBarsInsets.top
//            }
//            title = ""
//        })
        sceneView = findViewById(R.id.sceneView)
        loadingView = findViewById(R.id.loadingView)
        newModelButton = findViewById<ExtendedFloatingActionButton>(R.id.newModelButton).apply {
            // Add system bar margins
            val bottomMargin = (layoutParams as ViewGroup.MarginLayoutParams).bottomMargin
            doOnApplyWindowInsets { systemBarsInsets ->
                (layoutParams as ViewGroup.MarginLayoutParams).bottomMargin =
                    systemBarsInsets.bottom + bottomMargin
            }
            setOnClickListener { newModelNode() }
        }
        placeModelButton = findViewById<ExtendedFloatingActionButton>(R.id.placeModelButton).apply {
            setOnClickListener { placeModelNode() }
        }

        newModelNode()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_ar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        item.isChecked = !item.isChecked
        modelNode?.detachAnchor()
        modelNode?.placementMode = when (item.itemId) {
            R.id.menuPlanePlacement -> PlacementMode.PLANE_HORIZONTAL_AND_VERTICAL
            R.id.menuInstantPlacement -> PlacementMode.INSTANT
            R.id.menuDepthPlacement -> PlacementMode.DEPTH
            R.id.menuBestPlacement -> PlacementMode.BEST_AVAILABLE
            else -> PlacementMode.DISABLED
        }
        return super.onOptionsItemSelected(item)
    }

    fun placeModelNode() {
        modelNode?.anchor()
        placeModelButton.isVisible = false
        sceneView.planeRenderer.isVisible = false
    }

    fun newModelNode() {
        isLoading = true
        modelNode?.takeIf { !it.isAnchored }?.let {
            sceneView.removeChild(it)
            it.destroy()
        }
        val model = models[modelIndex]
        modelIndex = (modelIndex + 1) % models.size
        modelNode = ArModelNode(model.placementMode).apply {
            applyPoseRotation = model.applyPoseRotation
            loadModelAsync(
                context = this@ArActivity,
                lifecycle = lifecycle,
                glbFileLocation = model.fileLocation,
                autoAnimate = true,
                scaleToUnits = model.scaleUnits,
                // Place the model origin at the bottom center
                centerOrigin = Position(y = -1.0f)
            ) {
                sceneView.planeRenderer.isVisible = true
                isLoading = false
            }
            onAnchorChanged = { node, _ ->
                placeModelButton.isGone = node.isAnchored
            }
            onHitResult = { node, _ ->
                placeModelButton.isGone = !node.isTracking
            }
        }
        sceneView.addChild(modelNode!!)
        // Select the model node by default (the model node is also selected on tap)
        sceneView.selectedNode = modelNode
    }
}

////import android.R
//import android.os.Bundle
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import com.example.bloodar.CameraPermissionHelper.checkCameraPermission
//import com.example.bloodar.CameraPermissionHelper.requestCameraPermission
//import com.google.ar.core.ArCoreApk
//import com.google.ar.core.Session
//import com.google.ar.core.exceptions.UnavailableUserDeclinedInstallationException
//import com.google.ar.sceneform.ux.ArFragment
//import io.github.sceneview.ar.ArFragment
//import kotlinx.android.synthetic.main.activity_ar.*
//
//
//class ArActivity : AppCompatActivity() {
////    private val PERMISSION_REQUEST_CODE = 200
//    private lateinit var mSession: Session
//
//    private var arFragment: ArFragment? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_ar)
//
//        // Load model.glb from assets folder or http url
//        arFragment = supportFragmentManager.findFragmentById(R.id.arFragment) as ArFragment?
//        arFragment!!.setOnTapPlaneGlbModel("Cappuccino_cup.glb")
//        //        arFragment.setOnTapPlaneGlbModel("https://github.com/codemaker2015/3d-models/blob/master/glb_files/model.glb?raw=true");
//    }
//
//    // requestInstall(Activity, true) will triggers installation of
//// Google Play Services for AR if necessary.
//    private var mUserRequestedInstall = true
//
//    override fun onResume() {
//        super.onResume()
//
//        // Check camera permission.
//            // ARCore requires camera permission to operate.
//        if (!checkCameraPermission(this)) {
//            requestCameraPermission(this)
//            return
//            }
//
//        // Ensure that Google Play Services for AR and ARCore device profile data are
//        // installed and up to date.
//        try {
//            if (mSession == null) {
//                when (ArCoreApk.getInstance().requestInstall(this, mUserRequestedInstall)) {
//                    ArCoreApk.InstallStatus.INSTALLED -> {
//                        // Success: Safe to create the AR session.
//                        mSession = Session(this)
//                    }
//                    ArCoreApk.InstallStatus.INSTALL_REQUESTED -> {
//                        // When this method returns `INSTALL_REQUESTED`:
//                        // 1. ARCore pauses this activity.
//                        // 2. ARCore prompts the user to install or update Google Play
//                        //    Services for AR (market://details?id=com.google.ar.core).
//                        // 3. ARCore downloads the latest device profile data.
//                        // 4. ARCore resumes this activity. The next invocation of
//                        //    requestInstall() will either return `INSTALLED` or throw an
//                        //    exception if the installation or update did not succeed.
//                        mUserRequestedInstall = false
//                        return
//                    }
//                }
//            }
//        } catch (e: UnavailableUserDeclinedInstallationException) {
//            // Display an appropriate message to the user and return gracefully.
//            Toast.makeText(this, "TODO: handle exception $e", Toast.LENGTH_LONG)
//                .show()
//            return
//        } catch (e: Throwable) {
//
//            return  // mSession remains null, since session creation has failed.
//        }
//
//    }
//
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<String>,
//        results: IntArray,
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, results)
//        if (!checkCameraPermission(this)) {
//            Toast.makeText(
//                this,
//                "Camera permission is needed to run this application",
//                Toast.LENGTH_LONG
//            )
//                .show()
//            if (!CameraPermissionHelper.shouldShowRequestPermissionRationale(this)) {
//                // Permission denied with checking "Do not ask again".
//                CameraPermissionHelper.launchPermissionSettings(this)
//            }
//            finish()
//        }
//    }
//}