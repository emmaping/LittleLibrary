/********************************************************************
 * File Name:    Library.java
 *
 * Date Created: 2014年12月9日
 *
 * ------------------------------------------------------------------
 * Copyright (C) 2010 Symantec Corporation. All Rights Reserved.
 *
 *******************************************************************/

// PACKAGE/IMPORTS --------------------------------------------------
package com.littlelibrary.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * TODO: Update with a detailed description of the interface/class.
 *
 */
@Document
public class Library
{
  @Id
  private String bookId;
  private String bookName;
  private int totalNumber;
  private int curNumber;
  // @DBRef
  private List<User> users;

  public Library()
  {
  }

  public Library(String bookName)
  {
    this(bookName, 1, 1, null);
  }

  public Library(String bookName, int totalNumber)
  {
    this(bookName, totalNumber, totalNumber, null);
  }

  public Library(String bookName, int totalNumber, int curNumber, List<User> users)
  {
    this.bookName = bookName;
    this.totalNumber = totalNumber;
    this.curNumber = curNumber;
    this.users = users;
  }

  @Override
  public String toString()
  {
    return "Library [bookName=" + bookName + ", totalNumber=" + totalNumber + ", curNumber=" + curNumber + ", borrower="
        + users.toString()
        + "]";
  }

  @Override
  public int hashCode()
  {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((bookId == null) ? 0 : bookId.hashCode());
    result = prime * result + ((bookName == null) ? 0 : bookName.hashCode());
    result = prime * result + curNumber;
    result = prime * result + totalNumber;
    result = prime * result + ((users == null) ? 0 : users.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj)
  {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Library other = (Library) obj;
    if (bookId == null)
    {
      if (other.bookId != null)
        return false;
    }
    else if (!bookId.equals(other.bookId))
      return false;
    if (bookName == null)
    {
      if (other.bookName != null)
        return false;
    }
    else if (!bookName.equals(other.bookName))
      return false;
    if (curNumber != other.curNumber)
      return false;
    if (totalNumber != other.totalNumber)
      return false;
    if (users == null)
    {
      if (other.users != null)
        return false;
    }
    else if (!users.equals(other.users))
      return false;
    return true;
  }

  public String getBookId()
  {
    return bookId;
  }

  public void setBookId(String bookId)
  {
    this.bookId = bookId;
  }

  public String getBookName()
  {
    return bookName;
  }

  public void setBookName(String bookName)
  {
    this.bookName = bookName;
  }

  public int getTotalNumber()
  {
    return totalNumber;
  }

  public void setTotalNumber(int totalNumber)
  {
    this.totalNumber = totalNumber;
  }

  public int getCurNumber()
  {
    return curNumber;
  }

  public void setCurNumber(int curNumber)
  {
    this.curNumber = curNumber;
  }

  public List<User> getUser()
  {
    return users;
  }

  public void setUser(List<User> users)
  {
    if (users == null)
    {
      this.users = null;
    }
    else
    {
      this.users = new ArrayList<User>(users.size());
      for (User user : users)
      {
        this.users.add(user);
      }
    }

  }
}
