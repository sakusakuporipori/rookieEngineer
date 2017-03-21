package ECサイト;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class NewECsite {
	public static void main(String[] args) throws IOException {
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(is);
		String line1 = br.readLine();     //入力

		String[] numdelimiter = line1.split(" ", 2);     //半角スペース区切り　領域は2
		int goodsnum = Integer.parseInt(numdelimiter[0]);     //商品の総数
		int CampaignDay = Integer.parseInt(numdelimiter[1]);     //キャンペーン日数
		int[] PriceArray = new int[goodsnum];

		//商品の値段を入力
		for(int i = 0; i < goodsnum; i++) {
			String line2 = br.readLine();
			PriceArray[i] = Integer.parseInt(line2);
		}

		int[] PriceSetting = new int[CampaignDay];     //キャンペーン日ごとの設定金額を格納する配列

		//設定金額を入力
		for(int j = 0; j < CampaignDay; j++) {
			String line3 = br.readLine();
			PriceSetting[j] = Integer.parseInt(line3);
		}

		ArrayList<Integer> PriceSum_List = new ArrayList<Integer>();     //2つの商品の合計金額を格納するリスト

		//2つの商品の合計金額を格納していく
		for(int a = 0; a < PriceArray.length-1; a++) {
			for(int b = a+1; b < PriceArray.length; b++) {     //a+1番目からはじめることにより、同じ要素番号とそれ以前の金額の計算を防ぐ
				PriceSum_List.add(PriceArray[a]+PriceArray[b]);
			}
		}

		int[] MostNearPriceSumArray = new int[PriceSetting.length];     //設定金額に最も近い金額を格納する配列

		for(int c = 0; c < PriceSetting.length; c++) {
			for(int d = 0; d < PriceSum_List.size(); d++) {
				if(d != PriceSum_List.size()-1) {     //d+1番目を評価と最後の要素になったときに配列オーバーを起こすためそれを防ぐ
					if(PriceSum_List.get(d+1) <= PriceSetting[c] && MostNearPriceSumArray[c] < PriceSum_List.get(d+1)) {
						MostNearPriceSumArray[c] = PriceSum_List.get(d+1);
					}
				} else {
					break;
				}
			}
		}

		for(int e = 0; e < MostNearPriceSumArray.length; e++) {
			if(MostNearPriceSumArray[e] > PriceSetting[e]) {     //リストから最初に格納したものが設定金額より高かった場合
				System.out.println(0);     //0と出力
			} else {
				System.out.println(MostNearPriceSumArray[e]);
			}
		}
	}
}
