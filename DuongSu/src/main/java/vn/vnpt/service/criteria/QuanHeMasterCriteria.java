package vn.vnpt.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link vn.vnpt.domain.QuanHeMaster} entity. This class is used
 * in {@link vn.vnpt.web.rest.QuanHeMasterResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /quan-he-masters?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class QuanHeMasterCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private LongFilter idDuongSu;

    private LongFilter idDuongSuQh;

    private Boolean distinct;

    public QuanHeMasterCriteria() {}

    public QuanHeMasterCriteria(QuanHeMasterCriteria other) {
        this.id = other.optionalId().map(LongFilter::copy).orElse(null);
        this.idDuongSu = other.optionalIdDuongSu().map(LongFilter::copy).orElse(null);
        this.idDuongSuQh = other.optionalIdDuongSuQh().map(LongFilter::copy).orElse(null);
        this.distinct = other.distinct;
    }

    @Override
    public QuanHeMasterCriteria copy() {
        return new QuanHeMasterCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public Optional<LongFilter> optionalId() {
        return Optional.ofNullable(id);
    }

    public LongFilter id() {
        if (id == null) {
            setId(new LongFilter());
        }
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LongFilter getIdDuongSu() {
        return idDuongSu;
    }

    public Optional<LongFilter> optionalIdDuongSu() {
        return Optional.ofNullable(idDuongSu);
    }

    public LongFilter idDuongSu() {
        if (idDuongSu == null) {
            setIdDuongSu(new LongFilter());
        }
        return idDuongSu;
    }

    public void setIdDuongSu(LongFilter idDuongSu) {
        this.idDuongSu = idDuongSu;
    }

    public LongFilter getIdDuongSuQh() {
        return idDuongSuQh;
    }

    public Optional<LongFilter> optionalIdDuongSuQh() {
        return Optional.ofNullable(idDuongSuQh);
    }

    public LongFilter idDuongSuQh() {
        if (idDuongSuQh == null) {
            setIdDuongSuQh(new LongFilter());
        }
        return idDuongSuQh;
    }

    public void setIdDuongSuQh(LongFilter idDuongSuQh) {
        this.idDuongSuQh = idDuongSuQh;
    }

    public Boolean getDistinct() {
        return distinct;
    }

    public Optional<Boolean> optionalDistinct() {
        return Optional.ofNullable(distinct);
    }

    public Boolean distinct() {
        if (distinct == null) {
            setDistinct(true);
        }
        return distinct;
    }

    public void setDistinct(Boolean distinct) {
        this.distinct = distinct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final QuanHeMasterCriteria that = (QuanHeMasterCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(idDuongSu, that.idDuongSu) &&
            Objects.equals(idDuongSuQh, that.idDuongSuQh) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idDuongSu, idDuongSuQh, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "QuanHeMasterCriteria{" +
            optionalId().map(f -> "id=" + f + ", ").orElse("") +
            optionalIdDuongSu().map(f -> "idDuongSu=" + f + ", ").orElse("") +
            optionalIdDuongSuQh().map(f -> "idDuongSuQh=" + f + ", ").orElse("") +
            optionalDistinct().map(f -> "distinct=" + f + ", ").orElse("") +
        "}";
    }
}
