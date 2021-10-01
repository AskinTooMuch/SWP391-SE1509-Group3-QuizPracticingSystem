/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.PostCate;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public interface PostCateINT {

    public ArrayList<PostCate> getAllPostCates();

    public PostCate getPostCateById(int pcId);

    public int updatePostCate(PostCate updatedPostCate);

    public int getBlogCateByBlogId(int blogId) throws Exception;

    public int deletePostCate(int pcId);

    public int addPostCate(PostCate newPostCate);
}
