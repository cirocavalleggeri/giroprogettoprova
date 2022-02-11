package com.example.ciro.terzaapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ciro.terzaapp.draw.DrawingView;

public class ActivityDraw extends AppCompatActivity {



        private TextView titolo;
        private DrawingView areaDisegno;
        private ImageView testo, freccia, sceltaColore, sceltaSpessore, undo, redo;
        private Bitmap drawable;

        private boolean isEnabledManoLibera = true;
        private boolean isEnabledLinea = false;
        private boolean isEnabledArrow = false;

        public ImageButton menuButton;


        private int mCurrentBackgroundColor;
        private int mCurrentColor;
        private int mCurrentStroke;
        private static final int MAX_STROKE_WIDTH = 50;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setNavigationBarColor(getResources().getColor(R.color.black));
            }
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_board1);

      /*      menuButton = findViewById(R.id.menu_button);

            menuButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Creating the instance of PopupMenu
                    PopupMenu popup = new PopupMenu( ActivityBoard1.this, menuButton);
                    //Inflating the Popup using xml file
                    popup.getMenuInflater()
                            .inflate(R.menu.menu, popup.getMenu());

                    //registering popup with OnMenuItemClickListener
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getTitle().toString())
                            {
                                case "Match Up":
                                    CaricaMainActivity("TabellaMatchUp");
                                    break;
                                case "Match Board":
                                    CaricaMainActivity("SceltaBoards");
                                    break;
                                case "Match Book":
                                    CaricaMainActivity("InfoBook");
                                    break;
                            }
                            return true;
                        }
                    });

                    popup.show(); //showing popup menu
                }
            });*/

           /* titolo = findViewById(R.id.titolo);

            titolo.setText("MATCHUP BOARD 1");*/

            areaDisegno = findViewById(R.id.area_disegno);
            testo = findViewById(R.id.testo);
            freccia = findViewById(R.id.freccia);
            sceltaColore= findViewById(R.id.scelta_colore);
            sceltaSpessore = findViewById(R.id.scelta_spessore);
            undo = findViewById(R.id.undo);
            redo = findViewById(R.id.redo);

            testo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    isEnabledArrow = false;
                    isEnabledLinea = false;
                    isEnabledManoLibera = false;
                    areaDisegno.drawText("classe di Chriss",areaDisegno.getmDrawCanvas());

                }
            });

            freccia.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    isEnabledArrow = true;
                    isEnabledLinea = false;
                    isEnabledManoLibera = false;
                }
            });

            sceltaColore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startColorPickerDialog();
                }
            });

            sceltaSpessore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startStrokeSelectorDialog();
                }
            });

            undo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    areaDisegno.undo();
                }
            });

            redo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    areaDisegno.redo();;
                }
            });



            inizializzaAreaDisegno();


        }

        public void CaricaMainActivity(String todo)
        {
            Intent mainActivity = new Intent(ActivityDraw.this, ActivityStarter.class);
            mainActivity.putExtra("todo", todo);
            startActivity(mainActivity);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }

        private void inizializzaAreaDisegno()
        {
            //   mCurrentBackgroundColor = ContextCompat.getColor(this, android.R.color.white);
            mCurrentColor = ContextCompat.getColor(this, android.R.color.black);
            mCurrentStroke = 10;

            areaDisegno.setBackgroundColor(mCurrentBackgroundColor);
            areaDisegno.setPaintColor(mCurrentColor);
            areaDisegno.setPaintStrokeWidth(mCurrentStroke);

        }


        private void startColorPickerDialog()
        {   mCurrentColor=0x435678;
           /* int[] colors = getResources().getIntArray(R.array.palette);
            String[] nomiColori = getResources().getStringArray(R.array.palette_colori);

            ColorPickerDialog dialog = ColorPickerDialog.newInstance(R.string.color_picker_default_title,
                    colors,
                    mCurrentColor,
                    6,
                    ColorPickerDialog.SIZE_SMALL);


            dialog.setOnColorSelectedListener(new ColorPickerSwatch.OnColorSelectedListener()
            {
                @Override
                public void onColorSelected(int color)
                {
                    int position = ArrayUtils.getArrayIndex(colors, color);

                    mCurrentColor = color;
                    areaDisegno.setPaintColor(mCurrentColor);
                    Log.d("DEBUG","Colore Scelto: " + mCurrentColor);
                    sceltaColore.setBackground(getDrawableByName(nomiColori[position], getApplicationContext()));
                }

            });


            dialog.show(getFragmentManager(), "ColorPickerDialog");
*/


        }

        public static Drawable getDrawableByName(String name, Context context) {
            int drawableResource = context.getResources().getIdentifier(name, "drawable", context.getPackageName());
            if (drawableResource == 0) {
                throw new RuntimeException("Can't find drawable with name: " + name);
            }//w  w  w  .j  ava  2 s  .co  m
            return context.getResources().getDrawable(drawableResource);
        }

        private void startStrokeSelectorDialog()
        {mCurrentStroke =10;
           /* StrokeSelectorDialog dialog = StrokeSelectorDialog.newInstance(mCurrentStroke, MAX_STROKE_WIDTH);

            dialog.setOnStrokeSelectedListener(new StrokeSelectorDialog.OnStrokeSelectedListener()
            {
                @Override
                public void onStrokeSelected(int stroke)
                {
                    mCurrentStroke = stroke;
                    areaDisegno.setPaintStrokeWidth(mCurrentStroke);
                }
            });

            dialog.show(getSupportFragmentManager(), "StrokeSelectorDialog");*/
        }

        private void startShareDialog(Uri uri)
        {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.setType("image/*");

            intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "");
            intent.putExtra(android.content.Intent.EXTRA_TEXT, "");
            intent.putExtra(Intent.EXTRA_STREAM, uri);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivity(Intent.createChooser(intent, "Share Image"));
        }

        private void requestPermissionsAndSaveBitmap()
        { //salva il bitmap in un file e condivide l'uri
//            if (PermissionManager.checkWriteStoragePermissions(this))
//            {
//                Uri uri = FileManager.saveBitmap(this, areaDisegno.getBitmap());
//                startShareDialog(uri);
//            }
        }

      /*      @Override
        public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
        {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            switch (requestCode)
        {
                case PermissionManager.REQUEST_WRITE_STORAGE:
                {
                    if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    {
                        Uri uri = FileManager.saveBitmap(this, areaDisegno.getBitmap());
                        startShareDialog(uri);
                    } else
                    {
                        Toast.makeText(this, "The app was not allowed to write to your storage. Hence, it cannot function properly. Please consider granting it this permission", Toast.LENGTH_LONG).show();
                    }
                }
            }
        }*/

        public static Bitmap drawableToBitmap (Drawable drawable) {
            Bitmap bitmap = null;

            if (drawable instanceof BitmapDrawable) {
                BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
                if(bitmapDrawable.getBitmap() != null) {
                    return bitmapDrawable.getBitmap();
                }
            }

            if(drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
                bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
            } else {
                bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            }

            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return bitmap;
        } } // end of class
