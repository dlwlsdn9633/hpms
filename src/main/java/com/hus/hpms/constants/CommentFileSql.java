package com.hus.hpms.constants;

public interface CommentFileSql
{
    public String FIND_COMMENT_FILE_BY_ID = "SELECT * FROM COMMENTFILE WHERE id=:id";
    public String FIND_COMMENT_FILE_BY_COMMENT_ID = "SELECT * FROM COMMENTFILE WHERE comment_id=:commentId";
}
