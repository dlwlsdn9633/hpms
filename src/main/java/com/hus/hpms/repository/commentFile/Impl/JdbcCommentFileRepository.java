package com.hus.hpms.repository.commentFile.Impl;

import com.hus.hpms.constants.CommentFileSql;
import com.hus.hpms.domain.CommentFile;
import com.hus.hpms.repository.commentFile.CommentFileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Repository
public class JdbcCommentFileRepository implements CommentFileRepository
{
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public JdbcCommentFileRepository(DataSource dataSource)
    {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("COMMENTFILE")
                .usingGeneratedKeyColumns("id");
    }

    public CommentFile save(CommentFile commentFile)
    {
        commentFile.setInsertDate(LocalDateTime.now());
        SqlParameterSource param = new BeanPropertySqlParameterSource(commentFile);
        commentFile.setId(simpleJdbcInsert.executeAndReturnKey(param).longValue());
        return commentFile;
    }

    @Override
    public List<CommentFile> findByCommentId(Long commentId)
    {
        Map<String, Object> param = Map.of("commentId", commentId);
        return namedParameterJdbcTemplate.query(CommentFileSql.FIND_COMMENT_FILE_BY_COMMENT_ID, param, commentFileRowMapper());
    }

    @Override
    public Optional<CommentFile> findById(Long id)
    {
        Map<String, Object> param = Map.of("id", id);
        try
        {
            CommentFile commentFile = namedParameterJdbcTemplate.queryForObject(CommentFileSql.FIND_COMMENT_FILE_BY_ID, param, commentFileRowMapper());
            assert commentFile != null;
            return Optional.of(commentFile);
        }
        catch(EmptyResultDataAccessException e)
        {
            log.error("error", e);
            return Optional.empty();
        }
    }

    private RowMapper<CommentFile> commentFileRowMapper()
    {
        return BeanPropertyRowMapper.newInstance(CommentFile.class);
    }
}
