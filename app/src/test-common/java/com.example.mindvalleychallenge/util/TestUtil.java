package com.example.mindvalleychallenge.util;

import com.example.mindvalleychallenge.data.models.Category;
import com.example.mindvalleychallenge.data.models.CategoryLinks;
import com.example.mindvalleychallenge.data.models.PinLinks;
import com.example.mindvalleychallenge.data.models.ProfileImage;
import com.example.mindvalleychallenge.data.models.Urls;
import com.example.mindvalleychallenge.data.models.User;
import com.example.mindvalleychallenge.data.models.UserLinks;
import com.example.mindvalleychallenge.data.remote.Pin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestUtil {

    public static PinLinks pinLinks(){
        return new PinLinks("https://api.unsplash.com/photos/sTBdWFQKDHE",
                "http://unsplash.com/photos/sTBdWFQKDHE",
                "http://unsplash.com/photos/sTBdWFQKDHE/download");
    }

    public static CategoryLinks categoryLinks(){
        return new CategoryLinks("https://api.unsplash.com/categories/2",
                "https://api.unsplash.com/categories/2/photos");
    }

    public static UserLinks userLinks(){
        return new UserLinks("https://api.unsplash.com/users/johanmouchet",
                "http://unsplash.com/@johanmouchet",
                "https://api.unsplash.com/users/johanmouchet/photos",
                "https://api.unsplash.com/users/johanmouchet/likes");
    }

    public static List<Category> categoryList(){
        return new ArrayList<Category>()
        {{
            add(new Category(2, "Buildings", 19563,categoryLinks()));
        add(new Category(2, "Buildings", 19563,categoryLinks()));
        }};
    }
    public static Urls urls(){
        return new Urls("https://images.unsplash.com/photo-1464519046765-f6d70b82a0df",
                "https://images.unsplash.com/photo-1464519046765-f6d70b82a0df?ixlib=rb-0.3.5\u0026q=80\u0026fm=jpg\u0026crop=entropy\u0026s=8cfd7aa940eb30ae2d754704c1ba89b5",
                "https://images.unsplash.com/photo-1464519046765-f6d70b82a0df?ixlib=rb-0.3.5\u0026q=80\u0026fm=jpg\u0026crop=entropy\u0026w=1080\u0026fit=max\u0026s=34a7fbfc57457c2423413a9534b7ecfd",
                "https://images.unsplash.com/photo-1464519046765-f6d70b82a0df?ixlib=rb-0.3.5\u0026q=80\u0026fm=jpg\u0026crop=entropy\u0026w=400\u0026fit=max\u0026s=b28e48601f9d332f5ba92c8d49cdbf83",
                "https://images.unsplash.com/photo-1464519046765-f6d70b82a0df?ixlib=rb-0.3.5\u0026q=80\u0026fm=jpg\u0026crop=entropy\u0026w=200\u0026fit=max\u0026s=5bae312df2a102d92a526b553f88de1e");
    }

    public static ProfileImage profileImage(){
        return new ProfileImage("https://images.unsplash.com/profile-1463936441736-1766d7a073d3?ixlib=rb-0.3.5\u0026q=80\u0026fm=jpg\u0026crop=faces\u0026fit=crop\u0026h=32\u0026w=32\u0026s=ad5c0beb8d0708261b8b710592f78c9b",
                "https://images.unsplash.com/profile-1463936441736-1766d7a073d3?ixlib=rb-0.3.5\u0026q=80\u0026fm=jpg\u0026crop=faces\u0026fit=crop\u0026h=64\u0026w=64\u0026s=174c2579f3ab2eb038df7aec3d33a8d5",
                "https://images.unsplash.com/profile-1463936441736-1766d7a073d3?ixlib=rb-0.3.5\u0026q=80\u0026fm=jpg\u0026crop=faces\u0026fit=crop\u0026h=128\u0026w=128\u0026s=11de9ff56c78eee1735d31e687854e65");
    }

    public static User user(){
        return new User("OevW4erf", "johnDoe", "John Doe", profileImage(), userLinks());
    }

    public static Pin pin(){
        return new Pin("123", "2016-05-29T15:42:02-04:00", 45, 67,
                "060607", 20, false, user(), null,
                urls(), categoryList(), pinLinks());
    }

    public static List<Pin> pinList(){
        return new ArrayList<Pin>()
        {{
            add(new Pin("123", "2016-05-29T15:42:02-04:00", 45, 67,
                    "060607", 9, false, user(), null,
                    urls(), categoryList(), pinLinks()));
            add(new Pin("456", "2019-05-29T15:42:02-04:00", 64, 23,
                    "060607", 15, false, user(), null,
                    urls(), categoryList(), pinLinks()));
        }};
    }

}
