/********************************************************************
 * File Name:    LibraryService.java
 *
 * Date Created: 2014年12月9日
 *
 * ------------------------------------------------------------------
 * Copyright (C) 2010 Symantec Corporation. All Rights Reserved.
 *
 *******************************************************************/

// PACKAGE/IMPORTS --------------------------------------------------
package com.littlelibrary.service;

import com.littlelibrary.domain.Library;
import com.littlelibrary.repository.LibraryRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibraryService
{
  @Autowired
  private LibraryRepository repo;

  @Autowired
  public LibraryService(LibraryRepository LibraryRepository)
  {
    this.repo = LibraryRepository;
  }

  public List<Library> findAll()
  {
    return repo.findAll();
  }

  public Library add(Library library)
  {
    if (null != library && null == repo.findByBookName(library.getBookName()))
    {
      return repo.save(library);
    }
    return null;
  }

  public Library update(Library library)
  {
    if (null == library)
    {
      return null;
    }

    Library book = repo.findByBookName(library.getBookName());
    if (null == book)
    {
      return repo.save(library);
    }
    else
    {
      book.setTotalNumber(library.getTotalNumber());
      book.setCurNumber(library.getCurNumber());
      book.setUser(library.getUser());
      return repo.save(book);
    }

  }

  public Library getLibraryByBookName(String bookName)
  {

    if ((bookName != null) && (bookName.length() != 0))
    {
      return repo.findByBookName(bookName);
    }

    return null;
  }

  public List<Library> getLibraryByUserName(String userName)
  {

    if ((userName != null) && (userName.length() != 0))
    {
      List<Library> library = repo.findByUsersUserName(userName);
      return library;
    }
    return null;
  }

  public List<Library> getLibraryByUserId(String userId)
  {

    if ((userId != null) && (userId.length() != 0))
    {
      List<Library> library = repo.findByUsersUserId(userId);
      return library;
    }

    return null;
  }

  public void deleteLibrary(String id)
  {

    repo.delete(id);
  }

  public Library getLibraryById(String id)
  {
    return repo.findOne(id);
  }

}
