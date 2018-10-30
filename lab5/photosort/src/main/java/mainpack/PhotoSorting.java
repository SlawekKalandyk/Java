package mainpack;

import io.indico.Indico;
import io.indico.api.*;
import io.indico.api.results.BatchIndicoResult;
import io.indico.api.utils.IndicoException;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

import javax.imageio.ImageIO;

import java.util.*;

public class PhotoSorting {
    private File dir;
    private String[] extensions = new String[]{"jpg"};

    public PhotoSorting(String directory) {
        this.dir = new File(directory);

        if(!this.dir.isDirectory())
            throw new InputIsNotDirectory();
    }

    public void photoSort() throws IndicoException {
        Indico indico = new Indico("2d9cd11e4c079d28757537030364bcf0");
        File[] images = dir.listFiles(imageFilter);
        BatchIndicoResult recognitionResult = null;

        try {
            Map<String, Object> params = new HashMap<>();
            params.put("top_n", 1);
            recognitionResult =
                    indico.imageRecognition.predict(images, params);
        } catch(IOException io) {
            io.printStackTrace();
        }

        List<Map<String, Double>> results = recognitionResult.getImageRecognition();

        for(int i = 0; i < results.size(); ++i) {
            System.out.println(results.get(i).keySet());
            System.out.println(images[i]);
            System.out.println();
        }
    }

    private FilenameFilter imageFilter = new FilenameFilter() {
        @Override
        public boolean accept(File dir, String name) {
            for(String ext : extensions) {
                if(name.endsWith("." + ext))
                    return true;
            }
            throw new FileIsNotImage();
        }
    };
}
