package ru.otus.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.domain.Book;
import ru.otus.spring.service.BookService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;

    @Override
    public long getCount() {
        return bookDao.getCount();
    }

    @Override
    public Book save(Book book) {
        return bookDao.insert(book);
    }

    @Override
    public List<Book> findAll() {
        return bookDao.getAll();
    }

    @Override
    public Book findById(long id) {
        return bookDao.getById(id);
    }

    @Override
    public Book updateById(long id, Book book) {
        return bookDao.updateById(id,book);
    }

    @Override
    public long deleteById(long id) {
        return bookDao.deleteById(id);
    }
}
