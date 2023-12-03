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

        blog.getImageList().add(image);
        blogRepository2.save(blog);

        return image;
    }

    public void deleteImage(Integer id){
        imageRepository2.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        Optional<Image> optionalImage = imageRepository2.findById(id);
        Image image = optionalImage.get();

        // calculate screen area
        String[] screenDimension = screenDimensions.split("X");
        int screenH = Integer.parseInt(screenDimension[0]);
        int screenW = Integer.parseInt(screenDimension[1]);

        // calculate image area
        String[] imageDimension = image.getDimensions().split("X");
        int imageH = Integer.parseInt(imageDimension[0]);
        int imageW = Integer.parseInt(imageDimension[1]);
        return (screenH*imageH) / (screenW*imageW);
    }
}
