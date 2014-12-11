/********************************************************************
 * File Name:    LibraryRepository.java
 *
 * Date Created: 2014年12月9日
 *
 * ------------------------------------------------------------------
 * Copyright (C) 2010 Symantec Corporation. All Rights Reserved.
 *
 *******************************************************************/

// PACKAGE/IMPORTS --------------------------------------------------
package com.littlelibrary.repository;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import com.littlelibrary.domain.Library;

import java.util.List;

import org.bson.types.ObjectId;
//import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

/**
 * TODO: Update with a detailed description of the interface/class.
 *
 */
// public interface LibraryRepository extends MongoRepository<Library, String>
// {
// public Library findByBookName(String bookName);
//
// public Library findByBookId(String bookId);
//
// public List<Library> findByUsersUserId(String userId);
//
// public List<Library> findByUsersUserName(String userName);
//
// }

@Service
public class LibraryRepository
{

  @Autowired
  private MongoTemplate mongoTemplate;

  public Library save(Library library)
  {
    mongoTemplate.save(library);
    return library;
  }

  public List<Library> findAll()
  {
    return mongoTemplate.findAll(Library.class);
  }

  public Library findOne(String bookId)
  {
    return mongoTemplate.findOne(query(where("bookId").is(bookId)), Library.class);
  }

  public void delete(String bookId)
  {
    mongoTemplate.findAndRemove(query(where("bookId").is(bookId)), Library.class);
  }

  public Library findByBookName(String bookName)
  {
    return mongoTemplate.findOne(query(where("bookName").is(bookName)), Library.class);
  }

  public Library findByBookId(String bookId)
  {
    return mongoTemplate.findOne(query(where("bookId").is(bookId)), Library.class);
  }

  public List<Library> findByUsersUserId(String userId)
  {
    // Query query = new Query(Criteria.where("users.$userId").is(new ObjectId(userId)));
    return mongoTemplate.find(query(Criteria.where("users._id").is(new ObjectId(userId))), Library.class);

  }

  public List<Library> findByUsersUserName(String userName)
  {
    // Query query = new Query(Criteria.where("users.$userId").is(new ObjectId(userId)));
    return mongoTemplate.find(query(Criteria.where("users.$userName").is(userName)), Library.class);

  }
}
