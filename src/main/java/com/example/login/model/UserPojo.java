package com.example.login.model;

import java.util.Collection;

import jakarta.persistence.*;

@Entity
@Table(name =  "user", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class UserPojo {
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	private String email;

	private String password;
	
/*It looks like you've posted a code snippet related to JPA (Java Persistence API) annotations, specifically a @ManyToMany relationship between entities User and Role.
 *  Let me break down the annotations and what they are used for:


@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL): 
This annotation establishes a many-to-many relationship between two entities, User and Role.
 The fetch attribute specifies that the related roles should be eagerly fetched along
  with the user entity. The cascade attribute specifies that all cascading operations
   (such as persist, merge, remove, and refresh) should be propagated from the user entity 
   to the associated roles.



@JoinTable: This annotation is used to define the details of the join table
 that will be used to store the many-to-many relationship between User and Role.

name = "users_roles": Specifies the name of the join table, which in this case is "users_roles".

joinColumns: Specifies the foreign key column(s) from the owning entity (User) that 
references the join table. In this case, it's specifying that the "user_id" column in the
 join table will be associated with the "id" column of the User entity.

inverseJoinColumns: Specifies the foreign key column(s) from the inverse entity (Role) that 
references the join table. Here, it indicates that the "role_id" column in the join table will 
be associated with the "id" column of the Role entity.

To summarize, this code snippet is configuring a many-to-many relationship between User 
and Role entities using the @ManyToMany annotation. It specifies that the relationship should 
be eagerly fetched and that cascading operations should be propagated. The details of the join 
table are specified using the @JoinTable annotation, including the join table name and the foreign
 key columns for both sides of the relationship.
 This setup allows you to associate multiple roles with each user and have the database manage 
 the relationship through the join table.
	
	
	
	
	*/
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
			name = "users_roles",
			joinColumns = @JoinColumn(
		            name = "user_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(
				            name = "role_id", referencedColumnName = "id"))

	private Collection<RolePojo> roles;
	/*The @ManyToMany annotation establishes a many-to-many relationship between users and roles
	          using a join table named userRole.

           In your UserPojo class, the roles field is a collection of RolePojo objects representing
            the roles associated with a user. When you load a UserPojo object from the database,
             the ORM framework will automatically populate the roles collection based on the records 
             in the userRole join table.
          */
	
	public UserPojo() {
	                     }

	public UserPojo(String firstName, String lastName, String email, String password, Collection<RolePojo> roles) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Collection<RolePojo> getRoles() {
		return roles;
	}
	public void setRoles(Collection<RolePojo> roles) {
		this.roles = roles;
	}

}
