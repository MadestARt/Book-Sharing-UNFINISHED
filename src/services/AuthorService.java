package services;

import dao.AuthorDao;
import dto.AuthorDto;
import entity.Author;

import java.util.List;
import java.util.stream.Collectors;

public final class AuthorService {

    private static final AuthorService INSTANCE = new AuthorService();

    private final AuthorDao authorDao = AuthorDao.getInstance();

    private AuthorService() {

    }

    public List<AuthorDto> getAllAuthors() {
        var authors = authorDao.getAll();
        var authorDtoList = authors.stream()
                .map(author -> AuthorDto.builder().id(author.getId()).name(author.getName()).secondName(author.getSeconName()).gender(author.getGender()).build())
                .collect(Collectors.toList());
        return authorDtoList;
    }

    public static AuthorService getInstance() {
        return INSTANCE;
    }
}
