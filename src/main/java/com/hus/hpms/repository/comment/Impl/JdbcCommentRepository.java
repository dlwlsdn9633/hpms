package com.hus.hpms.repository.comment.Impl;

import com.hus.hpms.constants.CommentSql;
import com.hus.hpms.domain.Comment;
import com.hus.hpms.repository.comment.CommentRepository;
import com.hus.hpms.repository.comment.CommentUpdateParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Repository
public class JdbcCommentRepository implements CommentRepository
{
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public JdbcCommentRepository(DataSource dataSource)
    {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("COMMENT")
                .usingGeneratedKeyColumns("id");
    }
    @Override
    public Comment save(Comment comment)
    {
        setInsertDateAndUpdateDate(comment);

        SqlParameterSource param = new BeanPropertySqlParameterSource(comment);
        Long id = simpleJdbcInsert.executeAndReturnKey(param).longValue();
        comment.setId(id);

        return comment;
    }

    @Override
    public Comment saveReply(Comment comment)
    {
        setInsertDateAndUpdateDate(comment);

        SqlParameterSource param = new BeanPropertySqlParameterSource(comment);
        Long id = simpleJdbcInsert.executeAndReturnKey(param).longValue();
        comment.setId(id);

        return comment;
    }

    private void setInsertDateAndUpdateDate(Comment comment)
    {
        comment.setInsertDate(LocalDateTime.now());
        comment.setUpdateDate(LocalDateTime.now());
    }

    @Override
    public void update(CommentUpdateParam commentUpdateParam)
    {
        commentUpdateParam.setUpdateDate(LocalDateTime.now());
        SqlParameterSource param = new BeanPropertySqlParameterSource(commentUpdateParam);
        namedParameterJdbcTemplate.update(CommentSql.UPDATE_COMMENT, param);
    }

    @Override
    public void delete(Long id)
    {
        Map<String, Object> param = Map.of("id", id);
        namedParameterJdbcTemplate.update(CommentSql.DELETE_COMMENT_BY_ID, param);
    }


    @Override
    public List<Comment> findAllByRequestId(String requestId)
    {
        Map<String, Object> param = Map.of("requestId", requestId);
        List<Comment> comments = namedParameterJdbcTemplate.query(CommentSql.FIND_ALL_COMMENTS_BY_REQUEST_ID, param, commentRowMapper());
        for (Comment comment : comments) {
            Map<String, Object> replyParam = Map.of("parentCommentId", comment.getId());
            List<Comment> replys = namedParameterJdbcTemplate.query(CommentSql.FIND_ALL_REPLYS_BY_COMMENT_ID, replyParam, commentRowMapper());
            comment.setReplys(replys);
        }
        return comments;
    }

    


    @Override
    public Optional<Comment> findById(Long id)
    {
        try
        {
            Map<String, Object> param = Map.of("id", id);
            Comment comment = namedParameterJdbcTemplate.queryForObject(CommentSql.FIND_COMMENT_BY_ID, param, commentRowMapper());
            assert comment != null;
            return Optional.of(comment);
        }
        catch (EmptyResultDataAccessException e)
        {
            log.error("error", e);
            return Optional.empty();
        }
    }

    @Override
    public List<Comment> findAllReplysByCommentId(Long commentId)
    {
        Map<String, Object> param = Map.of("parentCommentId", commentId);
        return namedParameterJdbcTemplate.query(CommentSql.FIND_ALL_REPLYS_BY_COMMENT_ID, param, commentRowMapper());
    }

    private RowMapper<Comment> commentRowMapper()
    {
        return BeanPropertyRowMapper.newInstance(Comment.class);
    }

}
