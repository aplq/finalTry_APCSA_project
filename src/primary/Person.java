/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tableviewfxmlexample;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author AMIT
 */
public class Person {    
 
        private SimpleStringProperty id;
        private SimpleStringProperty name;
        private SimpleStringProperty age;
        private final SimpleStringProperty email;
        
        private SimpleStringProperty primary;
        
        private SimpleStringProperty secondry;
        
 
        Person(String id, String name, String age, String email, String primary, String secondry) {
            
            this.id = new SimpleStringProperty(id);
            this.name = new SimpleStringProperty(name);
            this.age = new SimpleStringProperty(age);
            this.email = new SimpleStringProperty(email);
            
            this.primary = new SimpleStringProperty(primary);
            
            this.secondry = new SimpleStringProperty(secondry);
            
        }
 
    
     public String getSecondry() {
        return secondry.get();
    }

  
    public void setSecondry(String secondry) {
        this.secondry.set(secondry);
    }    
 
        
    public String getPrimary() {
        return primary.get();
    }

  
    public void setPrimary(String primary) {
        this.primary.set(primary);
    }
 
        
        /**
     * @return the id
     */
    public String getId() {
        return id.get();
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id.set(id);
    }

    /**
     * @return the name
     */
    public String getName() {
        return name.get();
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name.set(name);
    }

    /**
     * @return the age
     */
    public String getAge() {
        return age.get();
    }

    /**
     * @param age the age to set
     */
    public void setAge(String age) {
        this.age.set(age);
    }
        
        public String getEmail() {
            return email.get();
        }
 
        public void setEmail(String email) {
            this.email.set(email);
        }
    }
