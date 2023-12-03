package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog
        Optional<Blog> optionalBlog = blogRepository2.findById(blogId);
        Blog blog = optionalBlog.get();

        Image image = new Image(description, dimensions, blog);
        Image savedImage = imageRepository2.save(image);

        blog.getImageList().add(savedImage);
        Blog savedBlog = blogRepository2.save(blog);

        return savedImage;
    }

    public void deleteImage(Integer id){
//        Optional<Image> optionalImage = imageRepository2.findById(id);

        // get blog attached to given image
//        Image image = optionalImage.get();
//        Blog blog = image.getBlog();
//
//        // delete image from blog list & from DB
//        for(Image image1: blog.getImageList()) {
//            if(image1.equals(image)) {
//                blog.getImageList().remove(image1);
//                break;
//            }
//        }
//        Blog savedBlog = blogRepository2.save(blog);
        imageRepository2.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        Optional<Image> optionalImage = imageRepository2.findById(id);
        Image image = optionalImage.get();

        // calculate screen area
        String[] screenDimension = screenDimensions.split("X");
        int screenArea = Integer.parseInt(screenDimension[0]) * Integer.parseInt(screenDimension[1]);

        // calculate image area
        String[] imageDimension = image.getDimensions().split("X");
        int imageArea = Integer.parseInt(imageDimension[0]) * Integer.parseInt(imageDimension[1]);
        return screenArea / imageArea;
    }
}
