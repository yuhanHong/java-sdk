/*
 * *
 *  * Copyright 2015 IBM Corp. All Rights Reserved.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.ibm.watson.developer_cloud.concept_insights.v2;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.ibm.watson.developer_cloud.concept_insights.v2.model.*;
import com.ibm.watson.developer_cloud.service.Request;
import com.ibm.watson.developer_cloud.service.WatsonService;
import com.ibm.watson.developer_cloud.util.*;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpRequestBase;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.MissingFormatArgumentException;

/**
 * The IBM Watson™ Concept Insights service provides APIs that enable you to work with concepts and identify
 * conceptual associations in the content that you provide as input to the service.
 * Input content is auto-tagged against a concept graph, which is a formal representation of the relationship(s) between concepts.
 * The concept graph used by the Concept Insights service is based on content that has been ingested from the English language Wikipedia
 *
 * @author Nizar Alseddeg (nmalsedd@us.ibm.com)
 * @version v2
 * @see <a
 * href="http://www.ibm.com/smarterplanet/us/en/ibmwatson/developercloud/concept_insights.html">
 * Concept Insights</a>
 */
public class ConceptInsights extends WatsonService {
    /**
     * The url.
     */
    private static final String URL = "https://gateway-s.watsonplatform.net/concept-insights-beta/api";
    /**
     * The ACCOUNTS_PATH.
     */
    public static final String ACCOUNTS_PATH = "/v2/accounts";

    /**
     * The GRAPHS_PATH.
     */
    public static final String GRAPHS_PATH = "/v2/graphs";

    /**
     * The CORPORA_PATH.
     */
    public static final String CORPORA_PATH = "/v2/corpora";

    /**
     * The LABEL_SEARCH.
     */
    public static final String LABEL_SEARCH = "label_search";

    /**
     * The ANNOTATIONS.
     */
    public static final String ANNOTATIONS = "annotations";

    /**
     * The RELATED_CONCEPTS.
     */
    public static final String RELATED_CONCEPTS = "related_concepts";

    /**
     * The Constant ACCOUNT_ID.
     */
    public static final String ACCOUNT_ID = "account_id";

    /**
     * The Constant GRAPHS.
     */
    public static final String GRAPHS = "graphs";

    /**
     * The Constant GRAPH NAME.
     */
    public static final String GRAPH = "graph";

    /**
     * The Constant IDS.
     */
    public static final String IDS = "ids";

    /**
     * The Constant query.
     */
    public static final String QUERY = "query";

    /**
     * The Constant PREFIX.
     */
    public static final String PREFIX = "prefix";

    /**
     * The Constant LIMIT.
     */
    public static final String LIMIT = "limit";

    /**
     * The Constant LEVEL.
     */
    public static final String LEVEL = "level";

    /**
     * The Constant CONCEPTS.
     */
    public static final String CONCEPTS = "concepts";

    /**
     * The Constant BODY.
     */
    public static final String BODY = "body";

    /**
     * The Constant ANNOTATE_TEXT.
     */
    public static final String ANNOTATE_TEXT = "annotate_text";

    /**
     * The Constant CONCEPT.
     */
    public static final String CONCEPT = "concept";

    /**
     * The Constant CONCEPTUAL_SEARCH.
     */
    public static final String CONCEPTUAL_SEARCH = "conceptual_search";

    /**
     * The Constant RELATION_SCORES.
     */
    public static final String RELATION_SCORES = "relation_scores";

    /**
     * The Constant CORPUS.
     */
    public static final String CORPUS = "corpus";

    /**
     * The Constant CURSOR.
     */
    public static final String CURSOR = "cursor";

    /**
     * The Constant DOCUMENT.
     */
    public static final String DOCUMENT = "document";

    /**
     * The Constant DOCUMENTS.
     */
    public static final String DOCUMENTS = "documents";

    /**
     * The Constant PROCESSING_STATE.
     */
    public static final String PROCESSING_STATE = "processing_state";

    /**
     * The Constant STATS.
     */
    public static final String STATS = "stats";

    /**
     * The Constant CONCEPT_FIELDS.
     */
    public static final String CONCEPT_FIELDS = "concept_fields";

    /**
     * The Constant DOCUMENT_FIELDS.
     */
    public static final String DOCUMENT_FIELDS = "document_fields";

    /**
     * Retrieves a Concept Insights account identified.
     *
     * @return the accounts
     */
    public Accounts getAccountsInfo() {
        Request request = Request.Get(ACCOUNTS_PATH);
        HttpRequestBase requestBase = request.build();
        try {
            HttpResponse response = execute(requestBase);
            JsonObject jsonObject = ResponseUtil.getJsonObject(response);
            Accounts accounts = GsonSingleton.getGson().fromJson(
                    jsonObject, Accounts.class);
            return accounts;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieves the available Graphs.
     *
     * @return the graphs
     */
    public Graphs getGraphs() {
        Request request = Request.Get(GRAPHS_PATH);
        HttpRequestBase requestBase = request.build();
        try {
            HttpResponse response = execute(requestBase);
            JsonObject jsonObject = ResponseUtil.getJsonObject(response);
            Graphs graphs = GsonSingleton.getGson().fromJson(
                    jsonObject, Graphs.class);
            return graphs;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Searches for graph concepts by using partial matches
     *
     * @param params String account_id the Account Id,
     *               String graph the graph name,
     *               String query the query string,
     *               boolean prefix Whether the query string should be treated as a prefix
     *               Integer limit the maximum number of items to be returned
     *               String concept_fields an additional fields to include in the concept objects. Format is a JSON object, e.g., { "abstract" : 1 } will include the concept abstracts in each concept object.
     * @return the matches
     */
    public Matches searchGraphsConceptByLabel(Map<String, Object> params) {
        Validate.notNull(params.get(ACCOUNT_ID), "account_id can't be null");
        Validate.notNull(params.get(GRAPH), "graph can't be null");
        Validate.notNull(params.get(QUERY), "query can't be null");
        String graph_id = createGraphId((String) params.get(ACCOUNT_ID), (String) params.get(GRAPH));
        Map<String, Object> queryParameters = new HashMap<String, Object>();
        String[] parameters = new String[]{QUERY,
                PREFIX, LIMIT};
        for (String param : parameters) {
            if (params.containsKey(param)) {
                queryParameters.put(param, params.get(param));
            }
        }
        return getConceptByLabel(graph_id + LABEL_SEARCH, queryParameters);
    }

    /**
     * Searches for graph concepts by using partial matches
     *
     * @param parameters String account_id the Account Id,
     *                   String graph the graph name,
     *                   List<String> concepts an array of concept IDs,
     *                   String concept the concept name
     *                   String concepts, Array of concept IDs, each identifying a concept. Must be string with a JSON format array, e.g., ["/graphs/wikipedia/en-20120601/concepts/IBM_Watson"]
     *                   Integer level a number in the range 0 - 3 that represents the level of popularity of related concepts
     *                   Integer limit the number of possible concepts to return.
     *                   String concept_fields an additional fields to include in the concept objects. Format is a JSON object, e.g., { "abstract" : 1 } will include the concept abstracts in each concept object.
     * @return the concepts
     */
    public Concepts getGraphsRelatedConcepts(Map<String, Object> parameters) {
        //TODO may be need to divide into 2 methods
        Validate.notNull(parameters.get(ACCOUNT_ID), "account_id can't be null");
        Validate.notNull(parameters.get(GRAPH), "graph can't be null");
        if (parameters.get(CONCEPTS) == null && parameters.get(CONCEPT) == null)
            throw new MissingFormatArgumentException("concepts or concept_id should be identified");
        String graphId = createGraphId((String) parameters.get(ACCOUNT_ID), (String) parameters.get(GRAPH));
        Map<String, Object> queryParameters = new HashMap<String, Object>();
        String[] queryParms = new String[]{LEVEL,
                LIMIT};
        for (String param : queryParms) {
            if (parameters.containsKey(param))
                queryParameters.put(param, parameters.get(param));
        }
        if (parameters.get(CONCEPTS) != null) {
            JsonObject contentJson = new JsonObject();
            JsonArray conceptsJson = new JsonArray();
            for (String value : (List<String>) parameters.get(CONCEPTS)) {
                conceptsJson.add(new JsonPrimitive(value));
            }
            contentJson.add(CONCEPTS, conceptsJson);
            queryParameters.put(CONCEPTS, conceptsJson.toString());
            return getRelatedConcepts(graphId + RELATED_CONCEPTS, queryParameters);
        } else {
            String conceptId = createConceptId((String) parameters.get(ACCOUNT_ID), (String) parameters.get(GRAPH), (String) parameters.get(CONCEPT));
            return getRelatedConcepts(conceptId + RELATED_CONCEPTS, queryParameters);
        }
    }

    /**
     * Identifies concepts in a piece of text
     *
     * @param params String account_id the Account Id,
     *               String graph the graph name,
     *               String body the text to analyze
     * @return the Annotations
     */
    public Annotations annotateText(Map<String, Object> params) {
        Validate.notNull(params.get(ACCOUNT_ID), "account_id can't be null");
        Validate.notNull(params.get(GRAPH), "graph can't be null");
        Validate.notNull(params.get(BODY), "body can't be null");
        String graphId = createGraphId((String) params.get(ACCOUNT_ID), (String) params.get(GRAPH));
        Request request = Request.Post(graphId + ANNOTATE_TEXT);
        request.withContent((String) params.get(BODY), MediaType.TEXT_PLAIN)
                .withHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON).build();
        HttpRequestBase requestBase = request.build();
        try {
            HttpResponse response = execute(requestBase);
            Annotations annotations = GsonSingleton.getGson().fromJson(
                    ResponseUtil.getString(response), Annotations.class);
            return annotations;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns information for a specific concept node in a graph
     *
     * @param params String account_id the Account Id,
     *               String graph the graph name,
     *               String concept the Concept name
     * @return the concept meta data
     */
    public ConceptMetaData getConcept(Map<String, Object> params) {
        Validate.notNull(params.get(ACCOUNT_ID), "account_id can't be null");
        Validate.notNull(params.get(GRAPH), "graph can't be null");
        Validate.notNull(params.get(CONCEPT), "concept can't be null");
        String conceptId = createConceptId((String) params.get(ACCOUNT_ID), (String) params.get(GRAPH), (String) params.get(CONCEPT));
        Request request = Request.Get(conceptId);
        HttpRequestBase requestBase = request.build();
        try {
            HttpResponse response = execute(requestBase);
            JsonObject jsonObject = ResponseUtil.getJsonObject(response);
            ConceptMetaData conceptMetaData = GsonSingleton.getGson().fromJson(
                    jsonObject, ConceptMetaData.class);
            return conceptMetaData;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns a list of scores that denotes how related a source concept is
     * to a list of individual concepts
     *
     * @param parameters String account_id the Account Id,
     *                   String graph the graph name,
     *                   String concept the Concept name
     * @return the scores
     */
    public Scores getGraphsRelationScores(Map<String, Object> parameters) {
        Validate.notNull(parameters.get(ACCOUNT_ID), "account_id can't be null");
        Validate.notNull(parameters.get(GRAPH), "graph can't be null");
        Validate.notNull(parameters.get(CONCEPT), "concept can't be null");
        Validate.notNull(parameters.get(CONCEPTS), "concepts can't be null");
        String conceptId = createConceptId((String) parameters.get(ACCOUNT_ID), (String) parameters.get(GRAPH), (String) parameters.get(CONCEPT));
        Map<String, Object> queryParameters = new HashMap<String, Object>();
        JsonObject contentJson = new JsonObject();
        JsonArray conceptsJson = new JsonArray();
        for (String value : (List<String>) parameters.get(CONCEPTS)) {
            conceptsJson.add(new JsonPrimitive(value));
        }
        contentJson.add(CONCEPTS, conceptsJson);
        queryParameters.put(CONCEPTS, conceptsJson.toString());
        return getRelationScores(conceptId + RELATION_SCORES, queryParameters);
    }

    /**
     * retrieves the available corpora.
     *
     * @return the Corpora
     */
    public Corpora listCorpora() {
        Request request = Request.Get(CORPORA_PATH);
        HttpRequestBase requestBase = request.build();
        try {
            HttpResponse response = execute(requestBase);
            JsonObject jsonObject = ResponseUtil.getJsonObject(response);
            Corpora corpora = GsonSingleton.getGson().fromJson(
                    jsonObject, Corpora.class);
            return corpora;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieves the available Corpora object associated with an account id
     *
     * @param accountId String account_id the Account Id,
     * @return the Corpora
     */
    public Corpora getCorpora(String accountId) {
        Validate.notNull(accountId, "account_id can't be null");
        Request request = Request.Get(CORPORA_PATH + FORWARD_SLASH + accountId);
        HttpRequestBase requestBase = request.build();
        try {
            HttpResponse response = execute(requestBase);
            String jsonString = ResponseUtil.getString(response);
            Corpora corpora = GsonSingleton.getGson().fromJson(
                    jsonString, Corpora.class);
            return corpora;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieves corpus object
     * to a list of individual concepts
     *
     * @param accountId String the Account Id,
     * @param corpus    String the corpus name.
     * @return the Corpus
     */
    public Corpus getCorpus(final String accountId, final String corpus) {
        Validate.notNull(accountId, "account_id can't be null");
        Validate.notNull(corpus, "corpus can't be null");
        Request request = Request.Get(createCorpusId(accountId, corpus));
        HttpRequestBase requestBase = request.build();
        try {
            HttpResponse response = execute(requestBase);
            String jsonString = ResponseUtil.getString(response);
            Corpus result = GsonSingleton.getGson().fromJson(
                    jsonString, Corpus.class);
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Delete a corpus by ID
     *
     * @param accountId String the Account Id,
     * @param corpus    String the corpus name.
     * @return void
     */
    public void deleteCorpus(final String accountId, final String corpus) {
        Validate.notNull(accountId, "account_id can't be null");
        Validate.notNull(corpus, "corpus can't be null");
        Request request = Request.Delete(createCorpusId(accountId, corpus));
        HttpRequestBase requestBase = request.build();
        executeWithoutResponse(requestBase);
    }

    /**
     * Update existing corpus meta-data (access and permissions)
     *
     * @param accountId String the Account Id,
     * @param corpus    String the corpus name.
     * @param body      String JSON the corpus object
     * @return void
     */
    public void updateCorpus(final String accountId, final String corpus, final String body) {
        // TODO Is better to let the user passing java objcet and we have a full handle of JSON
        Validate.notNull(accountId, "account_id can't be null");
        Validate.notNull(corpus, "corpus can't be null");
        Validate.notNull(body, "body can't be null");
        Request request = Request.Post(createCorpusId(accountId, corpus));
        request.withContent(body, MediaType.TEXT_PLAIN)
                .withHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON).build();
        HttpRequestBase requestBase = request.build();
        executeWithoutResponse(requestBase);
    }

    /**
     * Create an empty corpus
     *
     * @param accountId String the Account Id,
     * @param corpus    String the corpus name.
     * @param body      String JSON the corpus object
     * @return void
     */
    public void createCorpus(final String accountId, final String corpus, final String body) {
        // TODO Is better to let the user passing java objcet and we have a full handle of JSON
        Validate.notNull(accountId, "account_id can't be null");
        Validate.notNull(corpus, "corpus can't be null");
        Validate.notNull(body, "body can't be null");
        Request request = Request.Put(createCorpusId(accountId, corpus));
        request.withContent(body, MediaType.TEXT_PLAIN)
                .withHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON).build();
        HttpRequestBase requestBase = request.build();
        executeWithoutResponse(requestBase);
    }

    /**
     * Retrieves the document ids of a corpus
     *
     * @param params String account_id the Account Id,
     *               String corpus the corpus name,
     *               Integer cursor the number of possible items to return. Specify '0' to return the maximum value of 100,000..
     *               Integer limit the number of possible concepts to return.
     *               String query JSON object that allows to filter the list of documents. Valid values are {"status":"error"}, {"status":"processing"}, and {"status":"ready"} which allow to filter documents by status.
     * @return the Documents
     */
    public Documents listDocuments(Map<String, Object> params) {
        Validate.notNull(params.get(ACCOUNT_ID), "account_id can't be null");
        Validate.notNull(params.get(CORPUS), "corpus can't be null");
        String[] queryParameters = new String[]{CURSOR,
                LIMIT};
        Request request = Request.Get(createCorpusId((String) params.get(ACCOUNT_ID), (String) params.get(CORPUS)) + DOCUMENTS);
        for (String param : queryParameters) {
            if (params.containsKey(param)) {
                request.withQuery(param, params.get(param));
            }
        }
        if (params.get(QUERY) != null) {
            // TODO need to work in the query format
            JsonObject contentJson = new JsonObject();
            JsonArray conceptsJson = new JsonArray();
            for (String value : (List<String>) params.get(QUERY)) {
                conceptsJson.add(new JsonPrimitive(value));
            }
            contentJson.add(QUERY, conceptsJson);
            request.withQuery(QUERY, conceptsJson.toString());
        }
        HttpRequestBase requestBase = request.build();
        try {
            HttpResponse response = execute(requestBase);
            JsonObject jsonObject = ResponseUtil.getJsonObject(response);
            Documents documents = GsonSingleton.getGson().fromJson(
                    jsonObject, Documents.class);
            return documents;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieves a document from a corpus.
     *
     * @param accountId String the Account Id,
     * @param corpus    String the corpus name.
     * @param document  String the document name.
     * @return the Document
     */
    public Document getDocument(final String accountId, final String corpus, final String document) {
        Validate.notNull(accountId, "account_id can't be null");
        Validate.notNull(corpus, "corpus can't be null");
        Validate.notNull(document, "document can't be null");
        Request request = Request.Get(createDocumentId(accountId, corpus, document));
        HttpRequestBase requestBase = request.build();
        try {
            HttpResponse response = execute(requestBase);
            JsonObject jsonObject = ResponseUtil.getJsonObject(response);
            Document result = GsonSingleton.getGson().fromJson(
                    jsonObject, Document.class);
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Creates a document in corpus
     *
     * @param accountId String the Account Id,
     * @param corpus    String the corpus name.
     * @param document  String the document name.
     * @param body      String the document object to create
     * @return void
     */
    public void createDocument(final String accountId, final String corpus, final String document, final Document body) {
        Validate.notNull(accountId, "account_id can't be null");
        Validate.notNull(corpus, "corpus can't be null");
        Validate.notNull(document, "document can't be null");
        Validate.notNull(body, "body can't be null");
        Request request = Request.Put(createDocumentId(accountId, corpus, document));
        request.withContent(GsonSingleton.getGson().toJson(body), MediaType.TEXT_PLAIN)
                .withHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON).build();
        HttpRequestBase requestBase = request.build();
        executeWithoutResponse(requestBase);
    }

    /**
     * Updates a document by ID
     *
     * @param accountId String the Account Id,
     * @param corpus    String the corpus name.
     * @param document  String the document name.
     * @param body      Document the document object to update
     * @return void
     */
    public void updateDocument(final String accountId, final String corpus, final String document, final Document body) {
        Validate.notNull(accountId, "account_id can't be null");
        Validate.notNull(corpus, "corpus can't be null");
        Validate.notNull(document, "document can't be null");
        Validate.notNull(body, "body can't be null");
        Request request = Request.Post(createDocumentId(accountId, corpus, document));
        request.withContent(GsonSingleton.getGson().toJson(body), MediaType.TEXT_PLAIN)
                .withHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON).build();
        HttpRequestBase requestBase = request.build();
        executeWithoutResponse(requestBase);
    }

    /**
     * Delete a document by ID
     *
     * @param accountId String the Account Id,
     * @param corpus    String the corpus name.
     * @param document  String the document name.
     * @return void
     */
    public void deleteDocument(final String accountId, final String corpus, final String document) {
        Validate.notNull(accountId, "account_id can't be null");
        Validate.notNull(corpus, "corpus can't be null");
        Validate.notNull(document, "document can't be null");
        Request request = Request.Delete(createDocumentId(accountId, corpus, document));
        HttpRequestBase requestBase = request.build();
        executeWithoutResponse(requestBase);
    }

    /**
     * Get processing state of a Corpus
     *
     * @param accountId String the Account Id,
     * @param corpus    String the corpus name.
     * @return the CorpusProcessingState
     */
    public CorpusProcessingState getCorpusProcessingState(final String accountId, final String corpus) {
        Validate.notNull(accountId, "account_id can't be null");
        Validate.notNull(corpus, "corpus can't be null");
        Request request = Request.Get(createCorpusId(accountId, corpus) + PROCESSING_STATE);
        HttpRequestBase requestBase = request.build();
        try {
            HttpResponse response = execute(requestBase);
            JsonObject jsonObject = ResponseUtil.getJsonObject(response);
            CorpusProcessingState corpusProcessingState = GsonSingleton.getGson().fromJson(
                    jsonObject, CorpusProcessingState.class);
            return corpusProcessingState;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Get processing state of a Corpus
     *
     * @param accountId String the Account Id,
     * @param corpus    String the corpus name.
     * @return the CorpusStats
     */
    public CorpusStats getCorpusStats(final String accountId, final String corpus) {
        Validate.notNull(accountId, "account_id can't be null");
        Validate.notNull(corpus, "corpus can't be null");
        Request request = Request.Get(createCorpusId(accountId, corpus) + STATS);
        HttpRequestBase requestBase = request.build();
        try {
            HttpResponse response = execute(requestBase);
            JsonObject jsonObject = ResponseUtil.getJsonObject(response);
            CorpusStats corpusStats = GsonSingleton.getGson().fromJson(
                    jsonObject, CorpusStats.class);
            return corpusStats;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Searches for documents and concepts by using partial matches on the label(s) fields.
     *
     * @param params String account_id, the Account Id,
     *               String corpus, the corpus name,
     *               String query, the Query String,
     *               boolean concepts, Whether to return concepts that have a label match,
     *               boolean prefix, Whether the query string should be treated as a prefix,
     *               Integer limit, Maximum number of items to be returned,
     *               String concept_fields, Additional fields to include in the concept objects. Format is a JSON object, e.g., { "abstract" : 1 } will include the concept abstracts in each concept object.
     *               String document_fields, Additional fields to include in the document objects. Format is a JSON object, e.g., { "user_fields" : 1 } will include the document user_fields member.
     * @return the matches
     */
    public Matches searchCorpusByLabel(Map<String, Object> params) {
        Validate.notNull(params.get(ACCOUNT_ID), "account_id can't be null");
        Validate.notNull(params.get(CORPUS), "corpus can't be null");
        Validate.notNull(params.get(QUERY), "query can't be null");
        String corpusId = createCorpusId((String) params.get(ACCOUNT_ID), (String) params.get(CORPUS));
        Map<String, Object> queryParameters = new HashMap<String, Object>();
        String[] parameters = new String[]{QUERY,
                PREFIX, LIMIT, CONCEPTS};
        for (String param : parameters) {
            if (params.containsKey(param))
                queryParameters.put(param, params.get(param));
        }
        // Concept Field JSON
        if (params.get(CONCEPT_FIELDS) != null) {
            JsonObject conceptFieldJson = new JsonObject();
            JsonArray conceptFieldJsonArray = new JsonArray();
            for (String value : (List<String>) params.get(CONCEPT_FIELDS)) {
                conceptFieldJsonArray.add(new JsonPrimitive(value));
            }
            conceptFieldJson.add(CONCEPT_FIELDS, conceptFieldJsonArray);
            queryParameters.put(CONCEPT_FIELDS, conceptFieldJsonArray.toString());
        }
        // document Field JSON
        if (params.get(DOCUMENT_FIELDS) != null) {
            JsonObject documentFieldJson = new JsonObject();
            JsonArray documentFieldJsonJsonArray = new JsonArray();
            for (String value : (List<String>) params.get(DOCUMENT_FIELDS)) {
                documentFieldJsonJsonArray.add(new JsonPrimitive(value));
            }
            documentFieldJson.add(DOCUMENT_FIELDS, documentFieldJsonJsonArray);
            queryParameters.put(DOCUMENT_FIELDS, documentFieldJsonJsonArray.toString());
        }
        return getConceptByLabel(corpusId + LABEL_SEARCH, queryParameters);
    }

    /**
     * Retrieves concepts that are related to an entire corpus
     *
     * @param parameters String account_id, the Account Id,
     *                   String corpus, the corpus name,
     *                   Integer level, Number in the range 0 - 3 that represents the level of popularity of related concepts,
     *                   Integer limit, Maximum number of items to be returned,
     *                   String concept_fields, Additional fields to include in the concept objects. Format is a JSON object, e.g., { "abstract" : 1 } will include the concept abstracts in each concept object.
     * @return the Concepts
     */
    public Concepts getCorpusRelatedConcepts(Map<String, Object> parameters) {
        Validate.notNull(parameters.get(ACCOUNT_ID), "account_id can't be null");
        Validate.notNull(parameters.get(CORPUS), "corpus can't be null");
        String corpusId = createCorpusId((String) parameters.get(ACCOUNT_ID), (String) parameters.get(CORPUS));
        Map<String, Object> queryParameters = new HashMap<String, Object>();
        String[] params = new String[]{LEVEL, LIMIT};
        for (String param : params) {
            if (parameters.containsKey(param))
                queryParameters.put(param, parameters.get(param));
        }
        if (parameters.get(CONCEPT_FIELDS) != null) {
            JsonArray conceptFieldJsonArray = new JsonArray();
            for (String value : (List<String>) parameters.get(CONCEPT_FIELDS)) {
                conceptFieldJsonArray.add(new JsonPrimitive(value));
            }
            queryParameters.put(CONCEPT_FIELDS, conceptFieldJsonArray.toString());
        }
        return getRelatedConcepts(corpusId + RELATED_CONCEPTS, queryParameters);
    }

    /**
     * Returns a list of scores that denotes how related an entire corpus is to a list of individual concepts
     *
     * @param parameters String account_id, the Account Id,
     *                   String corpus, the corpus name,
     *                   String concepts, References to Concept URIs.
     * @return the Scores
     */
    public Scores getCorpusRelationScores(Map<String, Object> parameters) {
        Validate.notNull(parameters.get(ACCOUNT_ID), "account_id can't be null");
        Validate.notNull(parameters.get(CORPUS), "corpus can't be null");
        Validate.notNull(parameters.get(CONCEPTS), "concepts can't be null");
        String corpusId = createCorpusId((String) parameters.get(ACCOUNT_ID), (String) parameters.get(CORPUS));
        Map<String, Object> queryParameters = new HashMap<String, Object>();
        JsonObject contentJson = new JsonObject();
        JsonArray conceptsJson = new JsonArray();
        for (String value : (List<String>) parameters.get(CONCEPTS)) {
            conceptsJson.add(new JsonPrimitive(value));
        }
        contentJson.add(CONCEPTS, conceptsJson);
        queryParameters.put(CONCEPTS, conceptsJson.toString());
        return getRelationScores(corpusId + RELATION_SCORES, queryParameters);
    }

    /**
     * Performs a conceptual search within a corpus.
     *
     * @param params String account_id, the Account Id,
     *               String corpus, the corpus name,
     *               String ids, String field JSON array of concept and/or document ids,
     *               Integer cursor, Number of items to skip,
     *               Integer limit, Number of possible documents to return,
     *               String concept_fields, Additional fields to include in the concept objects. Format is a JSON object, e.g., { "abstract" : 1 } will include the concept abstracts in each concept object.
     *               String document_fields, Additional fields to include in the document objects. Format is a JSON object, e.g., { "user_fields" : 1 } will include the document user_fields member.
     * @return the QueryConcepts
     */
    public QueryConcepts conceptualSearch(Map<String, Object> params) {
        Validate.notNull(params.get(ACCOUNT_ID), "account_id can't be null");
        Validate.notNull(params.get(CORPUS), "corpus can't be null");
        Validate.notNull(params.get(IDS), "ids can't be null");
        String corpusId = createCorpusId((String) params.get(ACCOUNT_ID), (String) params.get(CORPUS));
        Request request = Request.Get(corpusId + CONCEPTUAL_SEARCH);
        String[] queryParameters = new String[]{
                CURSOR, LIMIT};
        for (String param : queryParameters) {
            if (params.containsKey(param))
                request.withQuery(param, params.get(param));
        }
        JsonArray IdsJsonArray = new JsonArray();
        for (String value : (List<String>) params.get(IDS)) {
            IdsJsonArray.add(new JsonPrimitive(value));
        }
        request.withQuery(IDS, IdsJsonArray.toString());
        if (params.get(CONCEPT_FIELDS) != null) {
            JsonArray conceptFieldJsonArray = new JsonArray();
            for (String value : (List<String>) params.get(CONCEPT_FIELDS)) {
                conceptFieldJsonArray.add(new JsonPrimitive(value));
            }
            request.withQuery(CONCEPT_FIELDS, conceptFieldJsonArray.toString());
        }
        if (params.get(DOCUMENT_FIELDS) != null) {
            JsonArray documentFieldJsonJsonArray = new JsonArray();
            for (String value : (List<String>) params.get(DOCUMENT_FIELDS)) {
                documentFieldJsonJsonArray.add(new JsonPrimitive(value));
            }
            request.withQuery(DOCUMENT_FIELDS, documentFieldJsonJsonArray.toString());
        }
        HttpRequestBase requestBase = request.build();
        try {
            HttpResponse response = execute(requestBase);
            String jsonString = ResponseUtil.getString(response);
            QueryConcepts concepts = GsonSingleton.getGson().fromJson(
                    jsonString, QueryConcepts.class);
            return concepts;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieves conceptual view of document (including annotations).
     *
     * @param accountId String the Account Id,
     * @param corpus    String the corpus name,
     * @param document  String the document name.
     * @return the DocumentAnnotations
     */
    public DocumentAnnotations getDocumentAnnotations(final String accountId, final String corpus, final String document) {
        Validate.notNull(accountId, "account_id can't be null");
        Validate.notNull(corpus, "corpus can't be null");
        Validate.notNull(document, "document can't be null");
        String documentId = createDocumentId(accountId, corpus, document);
        Request request = Request.Get(documentId + ANNOTATIONS);
        HttpRequestBase requestBase = request.build();
        try {
            HttpResponse response = execute(requestBase);
            String jsonString = ResponseUtil.getString(response);
            DocumentAnnotations documents = GsonSingleton.getGson().fromJson(
                    jsonString, DocumentAnnotations.class);
            return documents;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieves processing state of document.
     *
     * @param accountId String the Account Id,
     * @param corpus    String the corpus name,
     * @param document  String the document name.
     * @return the DocumentProcessingState
     */
    public DocumentProcessingState getDocumentProcessingState(final String accountId, final String corpus, final String document) {
        Validate.notNull(accountId, "account_id can't be null");
        Validate.notNull(corpus, "corpus can't be null");
        Validate.notNull(document, "document can't be null");
        String documentId = createDocumentId(accountId, corpus, document);
        Request request = Request.Get(documentId + PROCESSING_STATE);
        HttpRequestBase requestBase = request.build();
        try {
            HttpResponse response = execute(requestBase);
            String jsonString = ResponseUtil.getString(response);
            DocumentProcessingState documentProcessingState = GsonSingleton.getGson().fromJson(
                    jsonString, DocumentProcessingState.class);
            return documentProcessingState;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieve concepts that are related (in conceptual sense) to a given document.
     *
     * @param parameters String account_id, the Account Id,
     *                   String corpus, the corpus name,
     *                   String document, the document name,
     *                   Integer level, number in the range 0 - 3 that represents the level of popularity of related concepts.
     *                   Integer limit, Number of possible concepts to return.
     *                   String concept_fields, Additional fields to include in the concept objects. Format is a JSON object, e.g., { "abstract" : 1 } will include the concept abstracts in each concept object.
     * @return the Concepts
     */
    public Concepts getDocumentRelatedConcepts(Map<String, Object> parameters) {
        Validate.notNull(parameters.get(ACCOUNT_ID), "account_id can't be null");
        Validate.notNull(parameters.get(CORPUS), "corpus can't be null");
        Validate.notNull(parameters.get(DOCUMENT), "document can't be null");
        String documentId = createDocumentId((String) parameters.get(ACCOUNT_ID), (String) parameters.get(CORPUS), (String) parameters.get(DOCUMENT));
        String[] queryParameters = new String[]{LEVEL, LIMIT};
        Map<String, Object> queryParams = new HashMap<String, Object>();
        for (String param : queryParameters) {
            if (parameters.containsKey(param))
                queryParams.put(param, parameters.get(param));
        }
        if (parameters.get(CONCEPT_FIELDS) != null) {
            JsonArray conceptFieldJsonArray = new JsonArray();
            for (String value : (List<String>) parameters.get(CONCEPT_FIELDS)) {
                conceptFieldJsonArray.add(new JsonPrimitive(value));
            }
            queryParams.put(CONCEPT_FIELDS, conceptFieldJsonArray.toString());
        }
        return getRelatedConcepts(documentId + RELATED_CONCEPTS, queryParams);
    }

    /**
     * Retrieve concepts that are related (in conceptual sense) to a given document.
     *
     * @param parameters String account_id, the Account Id,
     *                   String corpus, the corpus name,
     *                   String document, the document name,
     *                   String concepts, String field JSON array of concept IDs.s.
     * @return the Concepts
     */
    public Scores getDocumentRelationScores(Map<String, Object> parameters) {
        Validate.notNull(parameters.get(ACCOUNT_ID), "account_id can't be null");
        Validate.notNull(parameters.get(CORPUS), "corpus can't be null");
        Validate.notNull(parameters.get(DOCUMENT), "document can't be null");
        Validate.notNull(parameters.get(CONCEPTS), "document can't be null");
        String documentId = createDocumentId((String) parameters.get(ACCOUNT_ID), (String) parameters.get(CORPUS), (String) parameters.get(DOCUMENT));
        Map<String, Object> queryParams = new HashMap<String, Object>();
        JsonObject contentJson = new JsonObject();
        JsonArray conceptsJson = new JsonArray();
        for (String value : (List<String>) parameters.get(CONCEPTS)) {
            conceptsJson.add(new JsonPrimitive(value));
        }
        contentJson.add(CONCEPTS, conceptsJson);
        queryParams.put(CONCEPTS, conceptsJson.toString());
        return getRelationScores(documentId + RELATION_SCORES, queryParams);
    }

    private Matches getConceptByLabel(String resourcePath, Map<String, Object> queryParameters) {
        Request request = Request.Get(resourcePath);
        for (Map.Entry<String, Object> entry : queryParameters.entrySet()) {
            request.withQuery(entry.getKey(), entry.getValue());
        }
        HttpRequestBase requestBase = request.build();
        try {
            HttpResponse response = execute(requestBase);
            JsonObject jsonObject = ResponseUtil.getJsonObject(response);
            Matches matches = GsonSingleton.getGson().fromJson(
                    jsonObject, Matches.class);
            return matches;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Concepts getRelatedConcepts(String resourcePath, Map<String, Object> queryParameters) {
        Request request = Request.Get(resourcePath);
        for (Map.Entry<String, Object> entry : queryParameters.entrySet()) {
            request.withQuery(entry.getKey(), entry.getValue());
        }
        HttpRequestBase requestBase = request.build();
        try {
            HttpResponse response = execute(requestBase);
            JsonObject jsonObject = ResponseUtil.getJsonObject(response);
            Concepts concepts = GsonSingleton.getGson().fromJson(
                    jsonObject, Concepts.class);
            return concepts;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Scores getRelationScores(String resourcePath, Map<String, Object> queryParameters) {
        Request request = Request.Get(resourcePath);
        for (Map.Entry<String, Object> entry : queryParameters.entrySet()) {
            request.withQuery(entry.getKey(), entry.getValue());
        }
        HttpRequestBase requestBase = request.build();
        try {
            HttpResponse response = execute(requestBase);
            JsonObject jsonObject = ResponseUtil.getJsonObject(response);
            Scores scores = GsonSingleton.getGson().fromJson(
                    jsonObject, Scores.class);
            return scores;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String createCorpusId(final String accountId, final String corpus) {return String.format("%s/%s/%s/", CORPORA_PATH, accountId, corpus);}

    private String createGraphId(final String accountId, final String graph) {return String.format("%s/%s/%s/", GRAPHS_PATH, accountId, graph);}

    private String createConceptId(final String accountId, final String graph, final String concept) {return String.format("%s/%s/%s/%s/%s/", GRAPHS_PATH, accountId, graph, CONCEPTS, concept);}

    private String createDocumentId(final String accountId, final String corpus, final String document) {return String.format("%s/%s/%s/%s/%s/", CORPORA_PATH, accountId, corpus, DOCUMENTS, document);}

}
