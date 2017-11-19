package sample.Model;

import java.util.List;

/**
 * Created by Riso on 7/4/2017.
 */
public class VideoQuestion extends Question {

    // Video reference

    private String introPath;
    private String mainPath;


    public VideoQuestion(String theme, String title, String description, List<String> answers, String introVideo, String mainVideo) {
        super(theme, title, description, answers);
        this.introPath = introVideo;
        this.mainPath = mainVideo;
    }


    public Boolean hasVideo(){
        return true;
    }

    public String getIntroPath() {
        return introPath;
    }

    public String getMainPath() {
        return mainPath;
    }
}
