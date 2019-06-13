import { Source } from "./source";

export class Article {
    articleId: string;
    author: string;
    title: string;
    comments: string;
    description: string;
    url: string;
    urlToImage: string;
    publishedAt: string;
    content: string;
    source: Source;
    count: number;

}