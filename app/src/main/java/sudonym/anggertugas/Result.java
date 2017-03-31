package sudonym.anggertugas;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sudonym on 24/03/17.
 */

public class Result {

//    Integer id;
//    String nama;
//    String alamat;
//
//    public Integer getId() {
//        return id;
//    }
//
//    public String getNama() {
//        return nama;
//    }
//
//    public String getAlamat() {
//        return alamat;
//    }

    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("nama")
    @Expose
    private String nama;

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

}
