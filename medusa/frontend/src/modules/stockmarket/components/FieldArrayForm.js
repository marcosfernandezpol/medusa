import React from 'react'
import { connect } from 'react-redux';
import { Field, FieldArray, reduxForm } from 'redux-form'
import validate from '../../common/components/validate'

const renderField = ({ input, label, type, meta: { touched, error } }) => (
  <div>
    <label>{label}</label>
    <div>
      <input {...input} type={type} placeholder={label} />
      {touched && error && <span>{error}</span>}
    </div>
  </div>
);

const renderYears = ({ fields }) => (
  <ul>
    <ul>
      <button type="button" onClick={() => fields.push({})}>
        Add Element
      </button>
    </ul>
    {fields.map((year, index) => (
      <ul key={index}>
        <button type="button" title="Remove Year" onClick={() => fields.remove(index)}>
		  Delete Element
        </button>
        <h4>Year #{index + 1}</h4>
        <Field
          name={`${year}.year`}
          type="number"
          step="1"
          component={renderField}
          label="Year"
        />
        <Field
          name={`${year}.benefits`}
          type="number"
          component={renderField}
          label="Benefits"
        />
      </ul>
    ))}
  </ul>
);

let FieldArraysForm = (props) => {
  const { handleSubmit, pristine, reset, submitting } = props;
  return (
    <form onSubmit={handleSubmit}>
      <FieldArray name="benefitsList" component={renderYears} />
      <div>
        <button type="submit" disabled={submitting}>
          Submit
        </button>
        <button type="button" disabled={pristine || submitting} onClick={reset}>
          Clear Values
        </button>
      </div>
    </form>
  );
};



export default reduxForm({
  form: 'fieldArrays', // a unique identifier for this form
  validate,
})(FieldArraysForm);





