package ir.tdaapp.paymanyar.Model.ViewModels;

import ir.tdaapp.paymanyar.Model.Enums.FileUploadAnalizeTenderType;

//مربوط به فایل صفحه آنالیز مناقصات
public class VM_FileUploadAnalizeTender {
    int id;
    String path;
    FileUploadAnalizeTenderType type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public FileUploadAnalizeTenderType getType() {
        return type;
    }

    public void setType(FileUploadAnalizeTenderType type) {
        this.type = type;
    }
}
