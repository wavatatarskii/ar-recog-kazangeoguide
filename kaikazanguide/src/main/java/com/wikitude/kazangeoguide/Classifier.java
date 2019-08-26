package com.wikitude.kazangeoguide;

import android.graphics.Bitmap;

import java.util.List;

public interface Classifier {

    class Recognition {
        /**
         * идентификатор распознавания, уникален для каждой классификации.
         */
        private final String id;

        /**
         * Наименования объектов.
         */
        private final String title;

        /**
         * Преобразованы ли данные в байты или нет.
         */
        private final boolean quant;

        /**
         * Сортируемое значение вероятности соответствия наименования объекту.Чем выше, тем лучше.
         */
        private final Float confidence;
        /**
         * Конструктор интерфейса распознавания
         */
        public Recognition(
                final String id, final String title, final Float confidence, final boolean quant) {
            this.id = id;
            this.title = title;
            this.confidence = confidence;
            this.quant = quant;
        }

        public String getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public Float getConfidence() {
            return confidence;
        }

        @Override
        /**
         * конструктор выдачи данных пользователю в строковом формате
         */
        public String toString() {
            String resultString = "";
            /*if (id != null) {
                resultString += "[" + id + "] ";
            }*/

            if (title != null) {
                if (title=="[kulsharif]") {resultString += " Кул-Шариф ";}
                else
                    resultString += title + " ";
            }

            /*if (confidence != null) {
                resultString += String.format("(%.1f%%) ", confidence * 100.0f);
            }*/

            return resultString.trim();
        }
    }


    List<Recognition> recognizeImage(Bitmap bitmap);

    void close();
}
