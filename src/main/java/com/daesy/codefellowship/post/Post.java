package com.daesy.codefellowship.post;

import com.daesy.codefellowship.applicationUsers.ApplicationUser;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import javax.persistence.*;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    public String body;
    public String timeStamp;

    @ManyToOne
    public ApplicationUser applicationUser;

    public Post(){};

    public Post(String body){
        this.body = body;
        this.timeStamp = nowTime();
    }

    // Reference: https://www.javatpoint.com/java-get-current-date
    private String nowTime(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return formatter.format(now);
    }

}
