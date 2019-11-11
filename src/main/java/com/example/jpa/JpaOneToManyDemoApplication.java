package com.example.jpa;

import com.example.jpa.model.Comment;
import com.example.jpa.model.Post;
import com.example.jpa.repository.CommentRepository;
import com.example.jpa.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.applet.AppletContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
@EnableJpaAuditing
public class JpaOneToManyDemoApplication implements CommandLineRunner {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private RepositoryRestConfiguration restConfiguration;

    Post post;
    Comment comment;

    public static void main(String[] args) {
        SpringApplication.run(JpaOneToManyDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        restConfiguration.exposeIdsFor(Post.class,Comment.class);

        postRepository.deleteAllInBatch();
        commentRepository.deleteAllInBatch();


        post = new Post("Title_1","My fist title ","This is : My fist title  ^^");
        //post = new Post("Title_2","My second title ","This is : My second title  ^^");

        postRepository.save(post);

        post = new Post("Title_2","My second title ","This is : My second title  ^^");
        //post = new Post("Title_2","My second title ","This is : My second title  ^^");

        postRepository.save(post);

        comment = new Comment("This is my fist Comment of my first Post Title1",post);
        commentRepository.save(comment);
        comment = new Comment("This is my fist Comment of my first Post Title2",post);
        commentRepository.save(comment);


    }
}
