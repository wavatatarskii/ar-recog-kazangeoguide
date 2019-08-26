package com.wikitude.kazangeoguide;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.wikitude.kazangeoguide.EntranceActivity;
import com.wonderkiln.camerakit.CameraKitError;
import com.wonderkiln.camerakit.CameraKitEvent;
import com.wonderkiln.camerakit.CameraKitEventListener;
import com.wonderkiln.camerakit.CameraKitImage;
import com.wonderkiln.camerakit.CameraKitVideo;
import com.wonderkiln.camerakit.CameraView;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ClassifierActivity extends EntranceActivity {

    private static final String MODEL_PATH = "graph.tflite";
    private static final boolean QUANT = false;
    private static final String LABEL_PATH = "labels.txt";
    private static final int INPUT_SIZE = 224;


    private Classifier classifier;

    private Executor executor = Executors.newSingleThreadExecutor();
    private TextView textViewResult;
    private TextView textView;
    private Button btnDetectObject, btnToggleCamera;
    private ImageView imageViewResult;
    private CameraView cameraView;
    /**
     * Загрузчик окна рспознавания
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classifier);
        cameraView = findViewById(R.id.cameraView);
        imageViewResult = findViewById(R.id.imageViewResult);
        textViewResult = findViewById(R.id.textViewResult);
        textViewResult.setMovementMethod(new ScrollingMovementMethod());



        btnToggleCamera = findViewById(R.id.btnToggleCamera);
        btnDetectObject = findViewById(R.id.btnDetectObject);
/**
 * Обработчик события работы с CameraKit Api
 */
        cameraView.addCameraKitListener(new CameraKitEventListener() {
            @Override
            public void onEvent(CameraKitEvent cameraKitEvent) {

            }

            @Override
            public void onError(CameraKitError cameraKitError) {

            }

            @Override
            public void onImage(CameraKitImage cameraKitImage) {

                Bitmap bitmap = cameraKitImage.getBitmap();

                bitmap = Bitmap.createScaledBitmap(bitmap, INPUT_SIZE, INPUT_SIZE, false);

                imageViewResult.setImageBitmap(bitmap);

                final List<Classifier.Recognition> results = classifier.recognizeImage(bitmap);

                int length=results.toString().length();

                String a;
                a = results.toString();
                /*
                 */
/**
 * Обращения к БД для выдачи данных о здании после распознавания
 */
                if (a.equals("[kulsharif]")) {
                    String b="Мечеть Кул-Шариф";
                    textViewResult.setText(b);
                    textView=(TextView) findViewById(R.id.text1);
                    DatabaseAccess databaseAccess=DatabaseAccess.getInstance(getApplicationContext());
                    databaseAccess.open();
                    String building_name= b;
                    String text = databaseAccess.getBuildingInfo(building_name);
                    textView.setText(text);
                    textView.setMovementMethod(new ScrollingMovementMethod());
                    databaseAccess.close();
                }
                else if (a.equals("[chasha]")) {
                    String b= "Дворец Бракосочетаний (Центр семьи Казан)";
                    textViewResult.setText(b);
                    textView=(TextView) findViewById(R.id.text1);
                    String text = "Летом 2013 года в Казани прошла студенческая Универсиада. В город съехались студенты-спортсмены из многих стран мира. Казань долго готовилась к этому событию. Горожане старались сделать Казань краше и удобнее, чтобы гости остались довольны. На месте ветхих или пустынных мест строились масштабные проекты. Например, таким стал Центр семьи, или как его еще называют — Чаша. Центр семьи «Казан» — это ЗАГС со смотровой площадкой, забравшись на которую можно посмотреть на Кремль и на Дворец земледельцев.";
                    textView.setText(text);
                    textView.setMovementMethod(new ScrollingMovementMethod());
                }
                else if (a.equals("[clock]")) {
                    String b="Мемориальные часы памяти Габдуллы Тукая";
                    textViewResult.setText(b);
                    textView=(TextView) findViewById(R.id.text1);
                    String text = "Часы в арабском стиле появились в Казани относительно недавно, в 1999 году, и располагаются они в самом сердце города – на площади Тукая. Это не просто часы, а настоящий скульптурный шедевр, выполненный из бронзы. Циферблат расписан только арабскими буквами, а на самом верху виднеется фигура мальчика с дудочкой, пегас, управляющий повозкой, и муза, которая расположена над этим мальчиком. Также в разных сторонах циферблата выгравированы строки из произведений известного татарского поэта – Тукая. Впрочем, это скульптура посвящена именно ему. Автором этого скульптурного шедевра стал И.Н. Башмаков, а вот нанесением арабских надписей занимался Н. Наккаш. В наши дни эти часы, расположенные в начале улицы Баумана, часто называют «часами влюбленных», т.к. именно здесь часто назначаются свидания. Полюбили этот памятник и туристы, поскольку рядом находится гостиница. Архитекторы отмечают, что дизайн скульптуры довольно сложный и запутанный. Все элементы памятника установлены с ювелирной точностью. Кроме того, часы установлены на высокой колонне, поэтому их видно довольно далеко.";
                    textView.setText(text);
                    textView.setMovementMethod(new ScrollingMovementMethod());
                }
                else if (a.equals("[suumbike]")) {
                    String b="Башня Сююмбике";
                    textViewResult.setText(b);
                    textView=(TextView) findViewById(R.id.text1);
                    DatabaseAccess databaseAccess=DatabaseAccess.getInstance(getApplicationContext());
                    databaseAccess.open();
                    String building_name= b;
                    String text = databaseAccess.getBuildingInfo(building_name);
                    textView.setText(text);
                    textView.setMovementMethod(new ScrollingMovementMethod());
                    databaseAccess.close();
                }
                else if (a.equals("[kolokol]")) {
                    String b ="Колокольня Богоявленского собора";
                    textViewResult.setText(b);
                    textView=(TextView) findViewById(R.id.text1);
                    DatabaseAccess databaseAccess=DatabaseAccess.getInstance(getApplicationContext());
                    databaseAccess.open();
                    String building_name= b;
                    String text = databaseAccess.getBuildingInfo(building_name);
                    textView.setText(text);
                    textView.setMovementMethod(new ScrollingMovementMethod());
                    databaseAccess.close();
                }


                else textViewResult.setText("Переснимите фото");

                /*
                 */




            }



            @Override
            public void onVideo(CameraKitVideo cameraKitVideo) {

            }
        });
/**
 * Обработчик события фотографирования
 */
        btnToggleCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cameraView.toggleFacing();
            }
        });
/**
 * Обработчик события создания миниатюры изображения
 */
        btnDetectObject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cameraView.captureImage();
            }
        });
/**
 * Инициализация моели из файла
 */
        initTensorFlowAndLoadModel();
    }



    @Override
    protected void onResume() {
        super.onResume();
        cameraView.start();
    }

    @Override
    protected void onPause() {
        cameraView.stop();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                classifier.close();
            }
        });
    }
    /**
     * Конструктор инициализатора модели из файла
     */
    private void initTensorFlowAndLoadModel() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    classifier = TensorFlowImageClassifier.create(
                            getAssets(),
                            MODEL_PATH,
                            LABEL_PATH,
                            INPUT_SIZE,
                            QUANT);
                    makeButtonVisible();
                } catch (final Exception e) {
                    throw new RuntimeException("Error initializing TensorFlow!", e);
                }
            }
        });
    }



    private void makeButtonVisible() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                btnDetectObject.setVisibility(View.VISIBLE);
            }
        });
    }
}

