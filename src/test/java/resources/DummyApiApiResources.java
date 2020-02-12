package resources;
//enum is a special class in java which has collection of constants or methods
public enum DummyApiApiResources {

    AddEmployee("/api/v1/create"),
    getEmployee("api/v1/employees"),
    ;
    private  String resource;
    DummyApiApiResources(String resource) {
        this.resource= resource;
    }

    public String getResource(){
        return  resource;
    }
}
