/**
 *  Copyright(C) 2021, Group Tree - SWP391, SE1509, FA21
 *  Created on : Sep 23, 2021
 *  PostCateDAO Interface
 *  Quiz practicing system
 *
 *  Record of change:
 *  Date        Version     Author              Description
 *  23/9/21     1.0         ChucNVHE150618      First Deploy
 *  18/10/21    1.0         NamDHHE150519       Add comment
*/
package dao;

import bean.PostCate;
import java.util.ArrayList;

/**
 * Lớp này chứa các interface của PostCateDAOImpl
 *
 * @author NamDH
 */
public interface PostCateDAO {

    public ArrayList<PostCate> getAllPostCates() throws Exception;

    public PostCate getPostCateById(int pcId) throws Exception;

    public int updatePostCate(PostCate updatedPostCate) throws Exception;

    public int getBlogCateByBlogId(int blogId) throws Exception;

    public int deletePostCate(int pcId) throws Exception;

    public int addPostCate(PostCate newPostCate) throws Exception;
}
