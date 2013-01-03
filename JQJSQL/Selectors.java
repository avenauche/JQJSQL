package selectors;

import java.util.ArrayList;
import java.util.Collections;

public class Selectors {

    private String tableName = "";
    private String outSQL = "";
    private String selClauses;
    private String alias = "";
    private ArrayList selClauseList;

//    static {
//        Class<Selectors> SELECTORS = Selectors.class;
//    }
    public Selectors() {
        this.selClauses = "";
    }

    public Selectors(String tableName) {
        selClauseList = new ArrayList();
        this.selClauses = "";
        this.tableName = tableName;
    }

    public static Selectors $() {
        return new Selectors();
    }

    @Override
    public String toString() {
        return outSQL;
    }

    public static Selectors $(String selector) {
        return new Selectors(selector);
    }

    public Selectors selectAll() {
        outSQL = "SELECT * FROM " + this.tableName.toUpperCase() + ";";
        return this;
    }

    public Selectors select(String selectClause) {

        Collections.addAll(selClauseList, selectClause.split(","));
        for (int i = 0; i < selClauseList.size(); i++) {
            this.selClauses += selClauseList.get(i).toString() + ", ";
        }

        this.selClauses = this.selClauses.substring(0, this.selClauses.length() - 2);
        constructQuery();
        this.selClauses = "";

        return this;
    }

    public Selectors count(String selectClause) {

        String[] splittedSelection = selectClause.split(",");

        for (String tmp : splittedSelection) {
            tmp = "count(" + tmp + ")";
            selClauseList.add(tmp.replace(" ", ""));
        }

        for (int i = 0; i < selClauseList.size(); i++) {
            this.selClauses += selClauseList.get(i).toString() + ", ";
        }

        this.selClauses = this.selClauses.substring(0, this.selClauses.length() - 2);
        constructQuery();
        this.selClauses = "";

        return this;
    }

    public Selectors getAll() {
        return selectAll();
    }

    public Selectors get(String selectClause) {
        return select(selectClause);
    }

    public Selectors as(String alias) {
        this.alias = alias;

        for (int i = 0; i < selClauseList.size(); i++) {
            this.selClauses += selClauseList.get(i).toString() + ", ";
        }
        this.selClauses = this.selClauses.substring(0, this.selClauses.length() - 2);

        constructQuery();
        this.selClauses = "";
        return this;
    }

    private String constructQuery() {

//        String head = "SELECT ";
        String tableName = this.tableName.toUpperCase();
        String alias = this.alias.toUpperCase();

        if (this.alias.length() > 0) {
            this.outSQL = "SELECT " + this.selClauses + " AS " + alias + " FROM " + tableName + ";";
        } else {
            this.outSQL = "SELECT " + this.selClauses + " FROM " + tableName + ";";
        }

        return outSQL;
    }

    public static void main(String[] args) {
//        System.out.println("selectAll:");
//        System.out.println($("myTable1").selectAll());
//
//        System.out.println("\nselect(\"*\") :");
//        System.out.println($("myTable1").select("*"));
//
//        System.out.println("\nAuto toString()");
//        System.out.println($("myTable1").select("*"));
//
//        Selectors s = $("myTable").select("*,lastName").select("firstName,avenauche");
//        System.out.println("\nreturn selectorObj, toString() and multi-select :");
//        System.out.println(s);

//        System.out.println($("agri").count("actual").select("date, region, schedule, scheduleEstimate, actual, actualEstimate"));
//        System.out.println($("agri").select("date, region, schedule, scheduleEstimate, actual, actualEstimate").count("actual, actualEstimate"));
        System.out.println($("agri").count("*, firstName").count("lastName").as("id"));

        System.out.println($("agri").count("*").as("id").select("firstName").as("fName"));
        
    }
}
