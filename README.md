# [도구설명]
검토가 필요한 특정 확장의 `e.g)so, a` 파일들이 `No Matches`로 분류되어 검토가 어려울 경우 특정 확장자를 정의하여 해당 확장자와 일치하는 파일 중 `No Matches`에 해당하는 파일들에 라이선스 또는 컴포넌트를 할당하여 `Identified` 탭에서 해당 파일들을 추가 검토할 수 있습니다.


# [Workflow]
1) 스캔된 모든 파일 리스트를 조회합니다.
2-1) 1)에서 조회된 파일 리스트 중 오픈소스와 매치가 없는 파일을 분류 합니다.
2-2) 2-1)에서 분류된 파일들의 확장자들을 확인하고 사용자가 정의한 확장자가 동일한 파일들만 분류 합니다.

파일 라이선스로 할당한 경우,
3-1) 2-2)에서 분류된 파일들에 라이선스를 할당하고 파일의 상태 값을 `Identified`로 변경하면 해당 파일들을 `Identifeid`탭의 `File license only` 카테고리에서 확인 가능합니다.

캄포넌트로 할당한 경우,
3-2) 2-2)에서 분류된 파일들에 컴포넌트를 할당하고 파일의 상태 값을 `Identified`로 변경하면 해당 파일들을 `Identifeid`탭에서 할당된 컴포넌트에 종속된 파일로 확인 가능합니다.


# [사전준비사항]
- 이 도구를 실행 전 Workbench에 적용 대상 라이선스 또는 컴포넌트를 생성합니다.
- `config.properties`파일 설정
```
# 적용 대상 Scan Code를 입력합니다.
fossid.scancode=scan_name_5951

# `fossid.select`가 license 인 경우 라이선스를 할당하며 적용대상 license identifier를 입력합니다.
fossid.identifier=Need to Check License

# `fossid.select`가 component 인 경우 컴포넌트를 할당하며 적용대상 component_name을 입력합니다.
fossid.component=Need to Check License

# `fossid.select`가 component 인 경우 컴포넌트를 할당하며 적용대상 component_version을 입력합니다.
fossid.version=1

# 라이선스 또는 컴포넌트를 선택하여 분류할 수 있습니다. 적용 값: component or license
fossid.select=component

# 적용 대상 확장자를 ,로 구분하여 입력합니다. 스페이스 없이 입력하여 주시기 바랍니다.
fossid.extensions=a,o,jar
```


# [사용방법]
- 파라미터를 사용할 경우 `config.properties` 파일에 할당된 값 보다 우선적으로 사용됩니다.
- 라이선스로 할당하는 경우
```bash
$ java -jar .\fossid_extension_auto-id.jar --url https://fossid.url.com --username username --apikey apikey --scancode scancode --identifier license_identifier --select license --extensions a,o,jar
```
- 컴포넌트로 할당하는 경우
```bash
$ java -jar .\fossid_extension_auto-id.jar --url https://fossid.url.com --username username --apikey apikey --scancode scancode --component componet_name --version component_version --select component --extensions a,o,jar
```
- `config.properties`에 모든 값이 설정된 경우
```bash
$ java -jar .\fossid_extension_auto-id.jar
```