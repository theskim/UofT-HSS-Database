package com.birdhss.birdhss.course;

import jakarta.persistence.*;

@Entity
@Table(name="course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String dept;

    @Column(nullable = false)
    private int code;

    @Column(nullable = false)
    private String campus;

    @Column(nullable = false)
    private String cname;

    @Column(length = 255)
    private String url;

    @Column(nullable = false)
    private double workload;

    @Column(nullable = false)
    private String cavg;

    @Column(nullable = false)
    private String ctype;

    @Column(nullable = false)
    private boolean summer;

    @Column(nullable = false)
    private boolean fall;

    @Column(nullable = false)
    private boolean winter;

    // Constructors
    public Course(int id, String dept, int code, String campus, String cname,
                  String url, double workload, String cavg, String ctype, boolean summer,
                  boolean fall, boolean winter) {
        this.id = id;
        this.dept = dept;
        this.code = code;
        this.campus = campus;
        this.cname = cname;
        this.url = url;
        this.workload = workload;
        this.cavg = cavg;
        this.ctype = ctype;
        this.summer = summer;
        this.fall = fall;
        this.winter = winter;
    }

    public Course(int id, String dept, int code, String campus, String cname,
                  double workload, String cavg, String ctype, boolean summer,
                  boolean fall, boolean winter) {
        this.id = id;
        this.dept = dept;
        this.code = code;
        this.campus = campus;
        this.cname = cname;
        this.workload = workload;
        this.cavg = cavg;
        this.ctype = ctype;
        this.summer = summer;
        this.fall = fall;
        this.winter = winter;
    }

    // Dummy
    public Course() {
        this.id = 0;
        this.dept = "ABC";
        this.code = 123;
        this.campus = "X";
        this.cname = "N/A";
        this.workload = 5;
        this.cavg = "N";
        this.ctype = "HSS";
        this.summer = false;
        this.fall = false;
        this.winter = false;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getDept() {
        return dept;
    }

    public int getCode() {
        return code;
    }

    public String getCampus() {
        return campus;
    }

    public String getName() {
        return cname;
    }

    public String getUrl() {
        return url;
    }

    public double getWorkload() {
        return workload;
    }

    public String getAvg() {
        return cavg;
    }

    public String getType() {
        return ctype;
    }

    public boolean isOfferedInSummer() {
        return summer;
    }

    public boolean isOfferedInFall() {
        return fall;
    }

    public boolean isOfferedInWinter() {
        return winter;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public void setName(String cname) {
        this.cname = cname;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setWorkload(double workload) {
        this.workload = workload;
    }

    public void setAvg(String cavg) {
        this.cavg = cavg;
    }

    public void setType(String ctype) {
        this.ctype = ctype;
    }

    public void setSummer(boolean summer) {
        this.summer = summer;
    }

    public void setFall(boolean fall) {
        this.fall = fall;
    }

    public void setWinter(boolean winter) {
        this.winter = winter;
    }
}
