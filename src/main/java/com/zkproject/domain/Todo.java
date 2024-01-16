package com.zkproject.domain;

import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity()
@Table(name = "todo")
public class Todo  implements Serializable, Cloneable {
    private static final long serialVersionUID = 1L;


    boolean complete;

    @Column(nullable = false)
    String subject;

    @Column(nullable = false)
    Priority priority;

    @Column(nullable = false)
    Date date = new Date();

    @Column(nullable = false)
    String description;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user")
    User user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;

    public Todo() {
    }

    public Todo(Integer id, String subject, Priority priority, Date date, String description, User user) {
        this.id = id;
        this.subject = subject;
        this.priority = priority;
        this.date = date;
        this.description = description;
        this.user=user;
    }
    public Todo(String subject) {
        this.subject = subject;
        this.priority = Priority.LOW;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Todo other = (Todo) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    public static Todo clone(Todo todo) {
        try {
            return (Todo) todo.clone();
        } catch (CloneNotSupportedException e) {
            // not possible
        }
        return null;
    }

}
