package ladder.test;

import ladder.view.UserInterface;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerNameTest {
    @DisplayName("플레이어 이름 최대 글자 수 테스트")
    @Test
    void inputPlayerList() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // given
        String input = "ikjo,honux,crong,jk";
        InputStream is = new ByteArrayInputStream(input.getBytes());
        System.setIn(is);
        UserInterface userInterface = new UserInterface();
        Method method = userInterface.getClass().getDeclaredMethod("inputPlayerList");

        // when
        List<String> arrayList = (List<String>) method.invoke(userInterface);

        // then
        boolean result = arrayList.stream().allMatch(v -> v.length() <= 5);
        assertThat(result).isTrue();
    }
}
