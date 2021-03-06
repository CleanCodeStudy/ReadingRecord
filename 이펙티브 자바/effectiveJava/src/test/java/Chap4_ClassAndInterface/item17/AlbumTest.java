package Chap4_ClassAndInterface.item17;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

/**
 * Created by jyami on 2020/02/22
 */
class AlbumTest {

    @Test
    void titleChange() {
        Album album = new Album("앨범타이틀");
        album.getTitle().name = "앨범타이틀 변경";
        assertThat(album.getTitle().name).isEqualTo("앨범타이틀 변경");
    }
}
