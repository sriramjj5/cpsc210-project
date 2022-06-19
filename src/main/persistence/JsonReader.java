package persistence;

import model.Course;
import model.CourseList;
import model.School;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.Student;
import org.json.*;

// Represents a reader that reads details of a school from JSON data stored in file
// CITATION: Most code obtained from JsonSerializationDemo
//           URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads school from file and returns it;
    // throws IOException if an error occurs reading data from file
    public School read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseSchool(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    public String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses school from JSON object and returns it
    private School parseSchool(JSONObject jsonObject) {
        School s = new School();
        addStudents(s, jsonObject);
        return s;
    }

    // MODIFIES: s
    // EFFECTS: parses currentStudents from JSON object and adds them to school
    private void addStudents(School s, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("currentStudents");
        for (Object json : jsonArray) {
            JSONObject nextStudent = (JSONObject) json;
            addStudent(s, nextStudent);
        }
    }

    // MODIFIES: s
    // EFFECTS: parses Student from JSON object and adds it to school's currentStudents
    private void addStudent(School s, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("courses");
        String name = jsonObject.getString("name");
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        CourseList courses = new CourseList();
        Student st = new Student(name, username, password, courses);
        for (Object json : jsonArray) {
            JSONObject nextCourse = (JSONObject) json;
            addCourse(s, st, nextCourse);
        }
        s.getCurrentStudents().add(st);
    }

    // MODIFIES: st
    // EFFECTS: parses Course from JSON object and adds it to student's enrolled courses
    private void addCourse(School s, Student st, JSONObject jsonObject) {
        String courseID = jsonObject.getString("courseID");
        st.addCourse(courseID, s.getExistingCourses());
    }

}
