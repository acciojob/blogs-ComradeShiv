package com.driver.services;

import com.driver.models.Blog;
import com.driver.models.Image;
import com.driver.models.User;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BlogService {
    @Autowired
    BlogRepository blogRepository1;

    @Autowired
    UserRepository userRepository1;

    public Blog createAndReturnBlog(Integer userId, String title, String content) {
        //create a blog at the current time
        Optional<User> optionalUser = userRepository1.findById(userId);
        User user = optionalUser.get();

        // create & save blog
        Blog blog = new Blog(title, content, user);
        Blog savedBlog = blogRepository1.save(blog);

        // save user changes
        user.getBlogList().add(savedBlog);
        User savedUser = userRepository1.save(user);

        return savedBlog;
    }

    public void deleteBlog(int blogId){
        //delete blog and corresponding images
        Optional<Blog> optionalBlog = blogRepository1.findById(blogId);
        Blog blog = optionalBlog.get();
        User user = blog.getUser();

        for(Blog blog1: user.getBlogList()) {
            if(blog1.equals(blog)) {
                user.getBlogList().remove(blog1);
                break;
            }
        }

        // save changes
        userRepository1.save(user);
        // delete blog
//        blogRepository1.deleteById(blogId);
    }
}
