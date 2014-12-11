/********************************************************************
 * File Name:    User.java
 *
 * Date Created: 2014年12月9日
 *
 * ------------------------------------------------------------------
 * Copyright (C) 2010 Symantec Corporation. All Rights Reserved.
 *
 *******************************************************************/

// PACKAGE/IMPORTS --------------------------------------------------
package com.littlelibrary.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * TODO: Update with a detailed description of the interface/class.
 *
 */
@Document
public class User
{
  @Id
  private String userId;

  private String userName;
  private String password;
  private String email;

  public User()
  {
  };

  public User(String name, String password, String email)
  {
    this.userName = name;
    this.password = password;
    this.email = email;
  }

  public String getUserId()
  {
    return userId;
  }

  public void setUserId(String id)
  {
    this.userId = id;
  }

  public String getUserName()
  {
    return userName;
  }

  public void setUserName(String userName)
  {
    this.userName = userName;
  }

  public String getPassword()
  {
    return password;
  }

  public void setPassword(String password)
  {
    this.password = password;
  }

  public String getEmail()
  {
    return email;
  }

  public void setEmail(String email)
  {
    this.email = email;
  }

  @Override
  public int hashCode()
  {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((userName == null) ? 0 : userName.hashCode());
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
    User other = (User) obj;
    if (userName == null)
    {
      if (other.userName != null)
        return false;
    }
    else if (!userName.equals(other.userName))
      return false;
    return true;
  }

  @Override
  public String toString()
  {
    return "User [name=" + userName + ", password=" + password + ", email=" + email + "]";
  }

}
