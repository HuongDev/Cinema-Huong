package com.huong.appcinema

import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.Toast
import com.huong.appcinema.networking.Api
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private val GALLERY = 1
    private val CAMERA = 2
    lateinit var service: Api;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        openDatePicker()
        initAdapter()
        btnSelectPhoto.setOnClickListener { view -> selectPhoto() }
        btnCreateMovie.setOnClickListener { view -> postMovie() }

    }

    private fun postMovie() {
        val title = edtMovie.text
        val genre = spGenreMovie.toString()
        val releaseDate = edtOpeningDay.text
    }

    private fun openDatePicker(){
        val myCalendar = Calendar.getInstance()

        val date = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, monthOfYear)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val myFormat = "MM/dd/yy"
            val sdf = SimpleDateFormat(myFormat, Locale.US)
            edtOpeningDay.setText(sdf.format(myCalendar.time))
        }

        edtOpeningDay.setOnClickListener(){
            View -> DatePickerDialog(this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show()
        }
    }

    private fun initAdapter(){
        var list = arrayOf("Item 1", "Item 2", "Item 3")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, list)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spGenreMovie!!.setAdapter(adapter)
    }

    private fun selectPhoto(){
        val pictureDialog = AlertDialog.Builder(this)
        pictureDialog.setTitle("Select Action")
        val pictureDialogItems = arrayOf("Select photo from gallery", "Capture photo from camera")
        pictureDialog.setItems(pictureDialogItems) {
            dialog, which ->
            when (which) {
                0 -> choosePhotoFromGallary()
                1 -> takePhotoFromCamera()
            }
        }
        pictureDialog.show()
    }

    private fun choosePhotoFromGallary() {
        val galleryIntent = Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)

        startActivityForResult(galleryIntent, GALLERY)
    }

    private fun takePhotoFromCamera() {
        val intent = Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, CAMERA)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_CANCELED) {
            return
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                val contentURI = data.data
                try {
                    val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, contentURI)
                    imvCinema.setImageBitmap(bitmap)

                } catch (e: IOException) {
                    e.printStackTrace()
                    Toast.makeText(this, "Failed!", Toast.LENGTH_SHORT).show()
                }
            }

        } else if (requestCode == CAMERA) {
            val thumbnail = data!!.extras!!.get("data") as Bitmap
            imvCinema.setImageBitmap(thumbnail)
        }
    }

//    private fun postImage(data: Intent?) {
//        val selectedImage : Uri? = data?.data
//        val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
//        val cursor = contentResolver.query(selectedImage!!, filePathColumn, null, null, null);
//        assert (cursor != null)
//
//        cursor!!.moveToFirst();
//
//        val columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//        val filePath = cursor.getString(columnIndex);
//        cursor.close();
//
//        val file = File(filePath)
//
//        val reqFile = RequestBody.create(MediaType.parse("image/*"), file);
//        val body = MultipartBody.Part.createFormData("upload", file.name, reqFile);
//        val name = RequestBody.create(MediaType.parse("text/plain"), "upload_test");
//
////            Log.d("THIS", data.getData().getPath());
//
//        val req : retrofit2.Call<okhttp3.ResponseBody>  = service.postImage(body, name);
//        req.enqueue(object : Callback<ResponseBody> {
//            override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
//                Log.v("retrofit", "call failed")
//            }
//
//            override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
//                Log.v("retrofit", "call success")
//            }
//
//        })
//
//    }

}
