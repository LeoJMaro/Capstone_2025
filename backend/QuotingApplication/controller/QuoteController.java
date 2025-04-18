package QuotingApplication.controller;

import QuotingApplication.pojos.Quote;
import QuotingApplication.dataaccess.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(RESTNouns.VERSION_1 + RESTNouns.QUOTE)
public class QuoteController {

    @Autowired
    private QuoteRepository quoteRepository;

    // Get all quotes
    @GetMapping
    public Iterable<Quote> getAllQuotes() {
        return quoteRepository.findAll();
    }

    // Get quote by ID
    @GetMapping(RESTNouns.ID)
    public ResponseEntity<Quote> getQuoteById(@PathVariable(name = "id") int id) {
        Optional<Quote> quote = quoteRepository.findById(id);
        return quote.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Create new quote
    @PostMapping
    public ResponseEntity<Quote> createQuote(@RequestBody Quote quote) {
        Quote savedQuote = quoteRepository.save(quote);
        return new ResponseEntity<>(savedQuote, HttpStatus.CREATED);
    }

    // Update quote
    @PutMapping(RESTNouns.ID)
    public ResponseEntity<Quote> updateQuote(@PathVariable(name = "id") int id, @RequestBody Quote quote) {
        Optional<Quote> existingQuote = quoteRepository.findById(id);
        if (existingQuote.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        // Set the ID to ensure we're updating the right record
        // We need to preserve the customer ID since Quote extends Customer
//        int customerId = existingQuote.get().getCustomerId();
//        quote.setCustomerId(customerId);
//        quote.setQuoteId(id);

        Quote updatedQuote = quoteRepository.save(quote);
        return new ResponseEntity<>(updatedQuote, HttpStatus.OK);
    }

    // Delete quote
    @DeleteMapping(RESTNouns.ID)
    public ResponseEntity<Void> deleteQuote(@PathVariable(name = "id") int id) {
        if (!quoteRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        quoteRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Get quotes generated after a specific date
    @GetMapping("/after")
    public ResponseEntity<List<Quote>> getQuotesAfterDate(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        List<Quote> quotes = quoteRepository.findByGeneratedDateAfter(date);
        if (quotes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(quotes, HttpStatus.OK);
    }

    // Get quotes for a specific customer
    @GetMapping(RESTNouns.CUSTOMER + "/{customerId}")
    public ResponseEntity<List<Quote>> getQuotesByCustomerId(@PathVariable(name = "customerId") int customerId) {
        List<Quote> quotes = quoteRepository.findByCustomerId(customerId);
        if (quotes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(quotes, HttpStatus.OK);
    }
}

