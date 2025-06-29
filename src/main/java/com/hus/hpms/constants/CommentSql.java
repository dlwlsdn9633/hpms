package com.hus.hpms.constants;

public interface CommentSql
{
    public String FIND_COMMENT_BY_ID = "SELECT * FROM COMMENT WHERE id=:id";
    public String UPDATE_COMMENT = "UPDATE COMMENT SET comment=:comment, update_date=:updateDate WHERE id=:id";
    public String FIND_ALL_COMMENTS_BY_REQUEST_ID = "SELECT * FROM COMMENT WHERE request_id=:requestId AND parent_comment_id IS NULL";
    public String FIND_ALL_REPLYS_BY_COMMENT_ID = "SELECT * FROM COMMENT WHERE parent_comment_id=:parentCommentId";
    public String DELETE_COMMENT_BY_ID = "DELETE FROM COMMENT WHERE id=:id";
    
}
