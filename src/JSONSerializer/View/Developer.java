package JSONSerializer.View;

import JSONSerializer.Annotation.JsonIgnore;
import JSONSerializer.Annotation.JsonProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Developer {

    @JsonProperty (name = "Course")
    private String course = "GoJava7";

    @JsonProperty (name = "Project")
    private String project = "JavaCoreFinalProject";

    @JsonProperty (name = "Group")
    private String group = "1_3";

    @JsonProperty (name = "Developers")
    private String[] developers = {"Pavlo Dudkin","Volodymyr Khorenzhyi","Alexey Petrov"};

    @JsonProperty (name = "Mentor")
    private String mentor = "Yevhenii Skliarov";
}
