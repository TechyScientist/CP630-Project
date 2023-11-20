package net.johnnyconsole.cp630.project.persistence;

import javax.persistence.*;
import java.sql.Blob;

@Entity
@Table(name="cons3250_project_model")
@NamedQueries({
        @NamedQuery(name="Model.findByName", query="SELECT m FROM Model m WHERE m.name = :name")
})
public class Model {

    @Id
    private String name;
    private String classname;
    private Blob object;

    public Model() {}

    public Model(String name) {
        this.name = name;
    }

    public Model(String name, String classname, Blob object) {
        this.name = name;
        this.classname = classname;
        this.object = object;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public Blob getObject() {
        return object;
    }

    public void setObject(Blob object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return "Model[name=" + name +", classname=" + classname + ", object=" + object.toString() + "]";
    }
}
