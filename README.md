# keiba-scrape1
netkeiba.com様の競走馬情報ページをスクレイピングさせて頂いたものをcsvファイルに保存します
<br><br>
![ss_2022-03-09_20-50-58](https://user-images.githubusercontent.com/98932123/157600614-5ef0b692-c2da-4624-abda-88b8db041f4e.png)

## 使い方
1. ElementScrape.javaのString fp を任意のディレクトリ書き換える
2. カレントディレクトリをプロジェクトに移動したあとターミナルから以下のコマンドを実行
      1. javac -classpath lib/jsoup-1.14.3.jar:lib/commons-lang3-3.12.0.jar -sourcepath src -d classes src/horsedata/HorseScraper.java<br>
           windowsの場合は : を ; に置き換える
      2. jar cvfm HorseScraper.jar META-INF/MANIFEST.MF -C classes .<br>
3. jarファイルを実行します<br>
         java -jar HorseScraper.jar
4. URLを入力と出るので該当馬ページURLを入力
## 使用技術
* 言語　Java 11
* ライブラリ　jsoup, commons-lang
