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
public interface PostCateDAO {

    public ArrayList<PostCate> getAllPostCates() throws Exception;

    public PostCate getPostCateById(int pcId) throws Exception;

    public int updatePostCate(PostCate updatedPostCate) throws Exception;

    public int getBlogCateByBlogId(int blogId) throws Exception;

    public int deletePostCate(int pcId) throws Exception;

    public int addPostCate(PostCate newPostCate) throws Exception;
}
