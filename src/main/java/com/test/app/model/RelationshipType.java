package com.test.app.model;

public enum RelationshipType {

	// 本人
	SELF,

	// 不明
	UNKNOWN,
	
	// 関係種類
	FATHER, // 父
	MOTHER, // 母
	SON, // 息子
	DAUGHTER, // 娘
	OLDER_BROTHER, // 兄
	YOUNGER_BROTHER, // 弟
	OLDER_SISTER, // 姉
	YOUNGER_SISTER, // 妹
	GRANDFATHER, // 祖父
	GRANDMATHER, // 祖母
	GRANDSON, // 孫息子
	GRANDDAUGHTER, // 孫娘

	// 性別不問の関係種類
	SIBLING, // 兄弟姉妹
	SPOUSE, // 配偶者
	FREIEND, // 友

	// 三親等(relation in the third degree)
	UNCLE, // おじ
	AUNT, // おば
	NEPHEW, // 甥
	NIECE, // 姪
	GREAT_GRANDFATHRE, // 曾祖父 読み方：そうそふ 祖父母の父にあたる人。ひいじじ。曽祖。
	GREAT_GRANDMOTHER, // 曾祖母 読み方：そうそぼ 祖父母の母。 ひいばば。 おおおば。
	GREAT_GRANDCHILD, // ひ孫
	GREAT_GRANDSON, // ひ孫息子
	GREAT_GRANDDOUGHTER, // ひ孫娘

	// 姻族(Relatives by affinity)
	// 配偶者，配偶者の血族，血族の配偶者などを指します。“義理の○○”を指す時は全て後ろに“in-law”をつけます。

	HUSBAND, // 夫：husband
	WIFE, // 妻：wife
	// SPOUSE, // 配偶者：spouse
	FATHER_IN_LAW, // 義理の父：father-in-law
	MOTHER_IN_LAW, // 義理の母：mother-in-law
	SON_IN_LAW, // 義理の息子：son-in-law
	DAUGHTER_IN_LAW, // 義理の娘：daughter-in-law
	BROTHER_IN_LAW, // 義理の兄弟：brother-in-law
	SISTER_IN_LAW, // 義理の姉妹：sister-in-law

	// その他
	OTHER;

//	private final String relationshipType;
//
//	RelationshipType(String relationshipType) {
//	    this.relationshipType = relationshipType;
//	}
//
//	public String getRelationshipType() {
//		return relationshipType;
//	}
}
